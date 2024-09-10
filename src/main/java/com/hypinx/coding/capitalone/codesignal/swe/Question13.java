package com.hypinx.coding.capitalone.codesignal.swe;

import java.util.*;

// Incomplete
public class Question13 {
    public static void main(String[] args) {
        String[][] messages = new String[4][2];
        messages[0] = new String[]{"1", "Hello how r u"};
        messages[1] = new String[]{"2", "good ty"};
        messages[2] = new String[]{"2", "u"};
        messages[3] = new String[]{"1", "me too bro"};

        ChatApp chatApp = new ChatApp();


        String[] result = chatApp.solution(messages, 15, 5);
    }
    static class ChatApp {
        public static String[] solution(String[][] messages, int width, int userWidth) {
            List<String> chatWindow = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            String lineSeparator = "+" + new String(new char[width - 2]).replace("\0", "*") + "+";
            chatWindow.add(lineSeparator);
            for (String[] message : messages) {
                String user = message[0];
                String text = message[1];
                String[] words = text.split(" ");
                int currentLineLength = 0;
                for (String word : words) {
                    if (currentLineLength + word.length() > userWidth) {
                        if (user.equals("1")) {
                            sb.append("|");
                            sb.append(new String(new char[userWidth - currentLineLength]).replace("\0", " "));
                            sb.append("|");
                        } else {
                            sb.append("|");
                            sb.append(new String(new char[userWidth - currentLineLength]).replace("\0", " "));
                            sb.append(word);
                        }
                        chatWindow.add(sb.toString());
                        sb.setLength(0);
                        currentLineLength = word.length() + 1;
                    } else {
                        if (currentLineLength == 0) {
                            if (user.equals("1")) {
                                sb.append("|").append(word);
                            } else {
                                sb.append(new String(new char[userWidth - word.length()]).replace("\0", " "));
                                sb.append(word).append("|");
                            }
                        } else {
                            sb.append(" ").append(word);
                        }
                        currentLineLength += word.length() + 1;
                    }
                }
                if (user.equals("1")) {
                    sb.append(new String(new char[userWidth - currentLineLength]).replace("\0", " "));
                    sb.append("|");
                } else {
                    sb.append("|");
                    sb.append(new String(new char[userWidth - currentLineLength]).replace("\0", " "));
                }
                chatWindow.add(sb.toString());
                sb.setLength(0);
            }
            chatWindow.add(lineSeparator);
            return chatWindow.toArray(new String[0]);
        }
    }

}
