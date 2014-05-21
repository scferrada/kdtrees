import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sebastián
 * Date: 21-05-14
 * Time: 03:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class KDTreeView {

        public static void printNode(KDNode root) {
            int maxLevel = KDTreeView.maxLevel(root);

            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        private static void printNodeInternal(List<KDNode> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || KDTreeView.isAllElementsNull(nodes))
                return;

            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor, 0)));
            int firstSpaces = (int) Math.pow(2, (floor +1 )) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 2)) - 1;

            KDTreeView.printWhitespaces(firstSpaces);

            List<KDNode> newNodes = new ArrayList<KDNode>();
            for (KDNode node : nodes) {
                if (node != null) {
                    if(node instanceof KDLeaf){
                        System.out.println(((KDLeaf)node).getPoint());
                    } else{
                        System.out.print(((KDInternalNode) node).getLine());
                        newNodes.add(((KDInternalNode) node).getLeft());
                        newNodes.add(((KDInternalNode) node).getRight());
                    }
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }

                KDTreeView.printWhitespaces(betweenSpaces);
            }
            System.out.println("");

            for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    KDTreeView.printWhitespaces(firstSpaces - i);
                    if (nodes.get(j) == null) {
                        KDTreeView.printWhitespaces(endgeLines + endgeLines + i + 1);
                        continue;
                    }

                    if (nodes.get(j)instanceof KDInternalNode)
                        System.out.print("/");
                    else
                        KDTreeView.printWhitespaces(1);

                    KDTreeView.printWhitespaces(i + i - 1);

                    if (nodes.get(j) instanceof KDInternalNode)
                        System.out.print("\\");
                    else
                        KDTreeView.printWhitespaces(1);

                    KDTreeView.printWhitespaces(endgeLines + endgeLines - i);
                }

                System.out.println("");
            }

            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        private static void printWhitespaces(int count) {
            for (int i = 0; i < count; i++)
                System.out.print(" ");
        }

        private static int maxLevel(KDNode node) {
            if (node instanceof KDLeaf)
                return 0;
            KDInternalNode inode = (KDInternalNode) node;
            return Math.max(KDTreeView.maxLevel(inode.getLeft()), KDTreeView.maxLevel(inode.getRight())) + 1;
        }

        private static <T> boolean isAllElementsNull(List<T> list) {
            for (Object object : list) {
                if (object != null)
                    return false;
            }

            return true;
        }

}
