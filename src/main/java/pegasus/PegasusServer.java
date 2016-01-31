package pegasus;

import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;
import java.util.Random;

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
            Peon peon = new Peon(name, initialPeonHealth, initialPeonMoney, "home");
            sendMessage("Lowly peon " + name + " has joined this silly game.");
            out.println("You're at home. It's a stupid farm. Go somewhere today and explore!");
            String newPeonQuery = "INSERT INTO PEONS VALUES(\'" + name.toLowerCase()  + "\', 50, 20, 'home');";
            System.out.println(newPeonQuery);
            PeonUtils.executeSqlUpdate(newPeonQuery);
            while (isAlive) {
                inputLine = in.readLine();
                if (inputLine != null) {
                    if (inputLine.startsWith("kill")) {
                        // Tokenize and validate input.
                        String[] toKill = inputLine.split("\\s+");
                        if (toKill.length == 2) {
                            Peon peonToKill = PeonUtils.getPeon(toKill[1].toLowerCase());
                            startFight(peon, peonToKill);
                        } else {
                            out.println("Not a valid kill command.");
                        }
                    } else if (inputLine.startsWith("go")) {
                        // Tokenize and validate input.
                        String[] toGo = inputLine.split("\\s+");
                        if (toGo.length == 2) {
                            String direction = toGo[1];
                            PeonUtils.move(direction, peon.location());
                        } else {
                            out.println("Not a valid go command.");
                        }
                    } else {
                        out.println(peon.handleCommand(inputLine));
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
            Socket client = clients.get(name);
            try {
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                out.println("You have been killed. Goodbye.");
                clients.remove(name);
                client.close();
            } catch (IOException e) {
                Throwables.propagate(e);
            }
            sendMessage(name + " was killed.");
        }

    }

    private void startFight(Peon peon, Peon enemyPeon) {
        sendMessage(enemyPeon.getName() + " and " + peon.getName() + " are fighting!");
        while (peon.health() > 0 && enemyPeon.health() > 0) {
            int damageToEnemy = peon.fight();
            enemyPeon.takeDamage(damageToEnemy);
            sendMessage(enemyPeon.getName() +  " takes " + damageToEnemy);
            sendMessage(enemyPeon.getName() + " at " + enemyPeon.health() + " points left");
            int damage = enemyPeon.fight();
            peon.takeDamage(damage);
            sendMessage(peon.getName() +  " takes " + damage);
            sendMessage(peon.getName() + " at " + peon.health() + " points left");
            if (peon.health() <= 0 || enemyPeon.health() <= 0 ) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Throwables.propagate(e);
            }
         }
        if (peon.health() <= 0) {
            removePeon(peon.getName());
        }
        if (enemyPeon.health() <= 0) {
            removePeon(enemyPeon.getName());
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
