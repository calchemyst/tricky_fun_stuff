package pegasus;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;

/**
 * Created by annafuller on 1/5/16.
 */
public final class PegasusServer {
    private final int port;
    private final Map<String, Socket> clients;
    private static final int initialPeonMoney = 20;
    private static final int initialPeonHealth = 50;

    private static final String[] sqlScripts= {"create.sql", "init.sql",
            "rooms.sql", "weapons.sql", "crops.sql"};

    public PegasusServer(int port) {
        this.port = port;
        this.clients = Maps.newHashMap();
    }

    public void start() {
        initializeDb();
        try (ServerSocket ss = new ServerSocket(port)) {
            while (true) {
                Socket client = ss.accept();
                new Thread() {
                    @Override
                    public void run() {
                        handleClient(client);
                    }
                }.start();
            }
        } catch (IOException e) {
            Throwables.propagate(e);
        }
    }

    private void initializeDb() {
        for (String sqlScript : sqlScripts) {
            PeonUtils.executeSqlFile(PeonUtils.getFileContents(this.getClass(), sqlScript));
        }
    }

    private void handleClient(Socket client) {
        try (PrintWriter out =
                     new PrintWriter(client.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(client.getInputStream()))) {
            boolean isAlive = true;
            out.println("Welcome to the Peon adventure game, where you harvest and harvest...");
            out.println("What is your name, oh humble one? Choose a good one or beware.");
            String name = "";
            String inputLine;
            name = in.readLine();
            while (!registerPeon(name, client, out)) {
                name = in.readLine();
            }
            Peon peonContext = new Peon(name, initialPeonHealth, initialPeonMoney, "home");
            out.println("Today is your lucky day, Peon " + name);
            out.println("You're at home. It's a stupid farm. Go somewhere today and explore!");
            String newPeonQuery = "INSERT INTO PEONS VALUES(\'" + name  + "\', 50, 20, 'home');";
            System.out.println(newPeonQuery);
            PeonUtils.executeSqlUpdate(newPeonQuery);
            while (isAlive) {
                inputLine = in.readLine();
                if (inputLine != null) {
                    if (inputLine.equals("sleep")) {
                        out.println(peonContext.sleep());
                    } else if (inputLine.equals("eat")) {
                        out.println(peonContext.eat());
                    } else if (inputLine.equals("health")) {
                        out.println(peonContext.health());
                    } else if (inputLine.equals("location")) {
                        String location = peonContext.location();
                        out.println("You're currently at " + location);
                    } else if (inputLine.equals("west")) {
                        out.println(peonContext.go("west"));
                    } else if (inputLine.equals("east")) {
                        out.println(peonContext.go("east"));
                    } else if (inputLine.equals("north")) {
                        out.println(peonContext.go("north"));
                    } else if (inputLine.equals("south")) {
                        out.println(peonContext.go("south"));
                    } else if (inputLine.equals("help")) {
                        out.println(peonContext.help());
                    } else {
                        out.println("That is not proper peon speech. Try the help command?");
                    }
                } else {
                    removePeon(name);
                    isAlive = false;
                }
            }
        } catch (SocketException e) {
            // Do nothing.
        } catch (IOException e) {
            Throwables.propagate(e);
        }
    }

    private void removePeon(String name) {
        if (clients.containsKey(name)) {
            clients.remove(name);
            sendMessage(name + " was killed.");
        }

    }

    private boolean registerPeon(String name, Socket client, PrintWriter out) {
        if (!Strings.isNullOrEmpty(name)) {
            if (!clients.containsKey(name)) {
                clients.put(name, client);
                return true;
            } else {
                out.println("Some other peon already goes by " + name + ". Pick another humble name!");
                return false;
            }
        } else {
            return false;
        }
    }

    private void sendMessage(String message) {
        for (Socket client : clients.values()) {
            try {
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                out.println(message);
            } catch (IOException e) {
                Throwables.propagate(e);
            }
        }
    }

    public static void main(String[] args) {
        PegasusServer server = new PegasusServer(38000);
        server.start();
    }


}
