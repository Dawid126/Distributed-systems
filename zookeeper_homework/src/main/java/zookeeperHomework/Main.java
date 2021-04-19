package zookeeperHomework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
	    if(args.length != 2) {
	        System.out.println("Number of arguments has to be 2: IP:port and process name");
	        System.exit(1);
        }

	    final String zNode = "/z";
	    final String ipAndPort = args[0];
	    final String processName = args[1];

	    try{
            final NodeMonitor nodeMonitor = new NodeMonitor(zNode, ipAndPort, processName);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("'tree' to print znode tree, 'exit' to exit");
            while (true) {
                String line = br.readLine();
                switch(line){
                    case "tree" -> nodeMonitor.printTree();
                    case "exit" -> System.exit(-1);
                    default -> System.out.println("Wrong command - type tree or exit");
                }
            }
        } catch (IOException e) {
	        e.printStackTrace();
        }
    }
}
