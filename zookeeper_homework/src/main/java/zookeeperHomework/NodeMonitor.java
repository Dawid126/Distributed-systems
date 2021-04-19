package zookeeperHomework;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;

import java.io.IOException;
import java.util.List;


public class NodeMonitor implements Watcher {

    private static final int timeout = 10000;

    private final String zNode;
    private final ZooKeeper zooKeeper;

    private Process processInstance = null;
    private boolean isCreatedProcessRunning = false;
    private final String processName;

    public NodeMonitor(String zNode, String ipAndPort, String processName) throws IOException {
        this.zNode = zNode;
        this.processName = processName;

        this.zooKeeper = new ZooKeeper(ipAndPort, timeout, this);
        startWatch();
    }

    public void startWatch() {
        try {
            zooKeeper.exists(zNode, true);
            zooKeeper.getChildren(zNode, true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            System.out.println("You need to create the node first");
        }
    }

    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case NodeChildrenChanged -> {
                int numOfChildren = getNumOfChildren(zNode);
                System.out.println("Num of children: " + numOfChildren);
            }
            case NodeCreated -> {
                if (zNode.equals(event.getPath()))
                    startProcess();
            }
            case NodeDeleted -> {
                if (zNode.equals(event.getPath())) {
                    destroyProcess();
                }
            }
        }
    }

    public void printTree() {
        try
        {
            List<String> children = this.zooKeeper.getChildren(zNode, false);
            if(children.size() > 0) {
                System.out.println("Znode's " + zNode + " children:");
                printChildren(children, zNode);
            } else {
                System.out.println("This node has no children now");
            }

        }
        catch (InterruptedException e)
        { e.printStackTrace(); }
        catch (KeeperException e)
        { System.out.println("You need to create the node first"); }
    }

    private void printChildren(List<String> children, String base) throws InterruptedException, KeeperException
    {
        for (String child : children)
        {
            String path = base + "/" + child;
            System.out.println(path);
            List<String> deeperChildren = this.zooKeeper.getChildren(path, true);
            printChildren(deeperChildren, path);
        }
    }

    public int getNumOfChildren(String node) {
        int childrenCounter = 0;

        try {
            if (zooKeeper.exists(node, true) != null) {
                List<String> children = zooKeeper.getChildren(node, true);
                childrenCounter += children.size();
                for (String child : children) {
                    childrenCounter += getNumOfChildren(node + "/" + child);
                }
            }
        } catch (KeeperException | InterruptedException e) {
            System.out.println("Problem with counting number of children");
            e.printStackTrace();
        }

        return childrenCounter;
    }

    public void startProcess() {
        startWatch();
        if (isCreatedProcessRunning) return;

        System.out.println("Starting new process: " + processName);
        try {
            processInstance = Runtime.getRuntime().exec(processName); //
            isCreatedProcessRunning = true;
        } catch (IOException e) {
            System.out.println("Problem with starting new process: " + processName);
            e.printStackTrace();
        }
    }

    public void destroyProcess() {
        if (!isCreatedProcessRunning) return;

        System.out.println("Destroying previously created process: " + processName);
        processInstance.destroy();
        isCreatedProcessRunning = false;
    }
}
