package net.arbitman;

import net.arbitman.trie.Trie;

import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Trie trie = new Trie();

    trie.add("foo")
      .add("hippo")
      .add("hip")
      .add("goo");

    System.out.println(trie);
    System.out.println("beginsWith(hip): " + trie.findBeginsWith("hip"));

    System.out.println(trie);
    System.out.println("\thas hippo? " + (trie.hasString("hippo") ? "Yes" : "No"));
    System.out.println("\thas hip? " + (trie.hasString("hip") ? "Yes" : "No"));
    System.out.println("\thas something? " + (trie.hasString("something") ? "Yes" : "No") + "\n");

    trie.delete("hip");

    System.out.println(trie);
    System.out.println("\thas hippo? " + (trie.hasString("hippo") ? "Yes" : "No"));
    System.out.println("\thas hip? " + (trie.hasString("hip") ? "Yes" : "No"));

    trie.add("something");
    System.out.println(trie);
  }
}
