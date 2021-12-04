package net.arbitman.trie;

import java.util.*;

public class Trie {
  private final Map<Character, Trie> root;
  private boolean terminated;

  public Trie() {
    root = new HashMap<>();
    terminated = false;
  }

  public Trie add(String str) {
    Trie currentNode = this;

    for (int i = 0; i < str.length(); i++) {
      if (!currentNode.containsKey(str.charAt(i))) {
        currentNode.put(str.charAt(i), new Trie());
      }

      currentNode = currentNode.get(str.charAt(i));
    }

    currentNode.setTerminated(true);
    return this;
  }

  public Trie get(Character c) {
    return root.get(c);
  }

  public boolean containsKey(Character c) {
    return root.containsKey(c);
  }

  public boolean put(Character c, Trie trie) {
    if (!root.containsKey(c)) {
      root.put(c, trie);
      return true;
    }

    return false;
  }

  public Set<Character> keySet() {
    return root.keySet();
  }

  public boolean hasString(String str) {
    Trie currentNode = this;

    for (int i = 0; i < str.length(); i++) {
      if (!currentNode.containsKey(str.charAt(i))) {
        return false;
      }

      currentNode = currentNode.get(str.charAt(i));
    }

    return currentNode.isTerminated();
  }

  public boolean delete(String str) {
    Trie currentNode = this;

    for (int i = 0; i < str.length(); i++) {
      if (!currentNode.containsKey(str.charAt(i))) {
        return false;
      }

      currentNode = currentNode.get(str.charAt(i));
    }

    currentNode.setTerminated(false);

    return true;
  }

  private List<String> getWords(Trie node, String prefix) {
    List<String> words = new ArrayList<>();

    if (isTerminated()) {
      words.add(prefix);
    }

    for (Character c : node.keySet()) {
      if (node.get(c).isTerminated()) {
        words.add(prefix + c);
      }

      words.addAll(getWords(node.get(c), prefix + c));
    }

    return words;
  }

  public List<String> findBeginsWith(String prefix) {
    Trie currentNode = this;

    for (int i = 0; i < prefix.length(); i++) {
      if (!currentNode.containsKey(prefix.charAt(i))) {
        return new ArrayList<>();
      }

      currentNode = currentNode.get(prefix.charAt(i));
    }

    List<String> words = getWords(currentNode, prefix);
    words.add(prefix);
    return words;
  }

  public int countBeginsWith(String prefix) {
    return findBeginsWith(prefix).size();
  }

  public boolean isTerminated() {
    return terminated;
  }

  public Trie setTerminated(boolean terminated) {
    this.terminated = terminated;
    return this;
  }

  public String toString() {
    return String.join(", ", getWords(this, ""));
  }
}
