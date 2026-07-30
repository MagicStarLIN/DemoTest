package com.lcl.DataStructure.tree.nodetree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class NodeTreeTest {
    private final INodeTree tree = new INodeTreeimpl();

    @Test
    void insertsNodesOnBothSidesAndFindsThem() {
        Node root = tree.insertNode(8, null);
        root = tree.insertNode(3, root);
        root = tree.insertNode(10, root);

        assertEquals(3, root.getLeftChild().getData());
        assertEquals(10, root.getRightChild().getData());
        assertEquals(10, tree.findNode(10, root).getData());
        assertNull(tree.findNode(99, root));
    }

    @Test
    void deletesLeafOneChildAndTwoChildNodes() {
        Node root = null;
        for (int value : new int[]{8, 3, 10, 1, 6, 4, 7, 14, 13}) {
            root = tree.insertNode(value, root);
        }

        root = tree.deleteNode(1, root);
        root = tree.deleteNode(14, root);
        root = tree.deleteNode(3, root);

        assertNull(tree.findNode(1, root));
        assertNull(tree.findNode(14, root));
        assertNull(tree.findNode(3, root));
        assertNotNull(tree.findNode(4, root));
        assertNotNull(tree.findNode(6, root));
    }

    @Test
    void deletingRootReturnsTheReplacementRoot() {
        Node root = tree.insertNode(8, null);
        root = tree.insertNode(3, root);

        root = tree.deleteNode(8, root);

        assertEquals(3, root.getData());
    }
}
