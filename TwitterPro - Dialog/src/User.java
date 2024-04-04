import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class User {
    int postNUm =0;
    int flowingNum =0;
    int flowerNum =0;
    static boolean confirm = true;
    static boolean capcha = true;
    private String[][] comments = new String[200][10];
    private int[] likes = new int[200];
    public void likePost(int post) {
        likes[post]++;
    }
    public int getLikes(int post) {
        return likes[post];
    }
    public String getFlowers(int nuum) {
        return flowers[nuum];
    }

    public void setFlowers(String flowers) {
        this.flowers[flowerNum] = flowers;
        ++flowerNum;
    }
    public void setIngUn(int num){
        for (int i = num; i < flowingNum - 1; i++) {
            this.flowing[i] = flowing[i + 1];
        }
        this.flowing[flowingNum - 1] = null;
        flowingNum--;
//        this.flowing[num] = null;
    }
    public void setErUn(int num){
        for (int i = num; i < flowerNum - 1; i++) {
            this.flowers[i] = flowers[i + 1];
        }
        this.flowers[flowerNum - 1] = null;
        flowerNum--;
//        this.flowers[num] = null;
    }

    public String getFlowing(int nuuum) {
        return flowing[nuuum];
    }

    public void setFlowing(String flowing) {
        this.flowing[flowingNum] = flowing;
        ++flowingNum;
    }
    private String[] flowers = new String[300];
    private String[] flowing = new String[300];
    static boolean log = true;
    private String username;
    private String password;
    private String bio;
    private String[] posts = new String[200];
    public String getPosts(int num) {
        return posts[num];
    }
    public void setPosts(String posts) {
        this.posts[postNUm] = posts;
        ++postNUm;
    }

    public void editPost(int num, String newPost){
        this.posts[num] = newPost;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    public boolean checkPassA(String password)
    {
        for (int i = 0; i < password.length(); i++) {
            char check = password.charAt(i);
            if (Character.isAlphabetic(check)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkPassD(String password)
    {
        for (int i = 0; i < password.length(); i++) {
            char check = password.charAt(i);
            if (Character.isDigit(check)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkPass(String password){
        if (password.length() < 8 || password.length() > 20 || !checkPassA(password) || !checkPassD(password))
        {
            return false;
        }
        return true;
    }
    public boolean available(String username,User[] users){
        for (int i = 0; i < Main.len; i++) {
            if(username.equals(users[i].getUsername())){
                return false;
            }
        }
        return true;
    }
    static public void signUp(User user,User[] users){
        Scanner inputt = new Scanner(System.in);
        String username = JOptionPane.showInputDialog("Enter your username:");
        while (!user.checkPassA(username) || !user.checkPassD(username)){
            username = JOptionPane.showInputDialog("Username must be the combination of digits and alphabets.\nEnter your username:");
        }
        while (!user.available(username,users)){
            username = JOptionPane.showInputDialog("The username given is not available,try again:");
        }
        String password = JOptionPane.showInputDialog("Enter password:");
        int trry=0;
        while (!user.checkPass(password)){
            if (trry>=3){
                password = JOptionPane.showInputDialog("The given password is not valid, Try again!\nPassword must be between 8 to 20 characters and must be the combination of digits and alphabets.");
            }else {
                password = JOptionPane.showInputDialog("The given password is not valid, Try again!");
            }
            ++trry;
        }
        confirm(password);
        if (confirm){
            capcha();
        }
        if (confirm && capcha){
            user.setUsername(username);
            user.setPassword(password);
            String bio = JOptionPane.showInputDialog("Enter bio:");
            user.setBio(bio);
            ++Main.len;
        }
    }

    static public User login(User[] users){
        UserMenu userMenu = new UserMenu();
        Scanner inputt = new Scanner(System.in);
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");
        int tryy = 0;
        for (int i = 0; i < Main.len; i++)
        {
            if (Objects.equals(username, users[i].username))
            {
                if (Objects.equals(password, users[i].password))
                {
                    JOptionPane.showMessageDialog(null,"Welcome "+ username);
                    userMenu.menu(users[i], users);
                    return null;
                }
                else if (!Objects.equals(password, users[i].password))
                {
                    do
                    {
                        tryy++;
                        password = JOptionPane.showInputDialog("The password was incorrect.\nEnter password:");
                        if(tryy==5){
                            String answer = JOptionPane.showInputDialog("Do you want to continue?").toUpperCase();
                            if (answer.equals("NO")){
                                log = false;
                                return null;
                            }
                        }

                    } while (!Objects.equals(password, users[i].password));

                    JOptionPane.showMessageDialog(null,"Welcome "+ username);
                    userMenu.menu(users[i], users);
                    return null;
                }
            }
        }
        JOptionPane.showMessageDialog(null,"Username not found!");
        return null;
    }

    public String getComment(int post, int comment) {
        return comments[post][comment];
    }

    public void addComment(int post, String comment) {
        for (int i = 0; i < comments[post].length; i++) {
            if (comments[post][i] == null) {
                comments[post][i] = comment;
                break;
            }
        }
    }

    public void deletePost(int numPOst) {
        if (numPOst >= 0 && numPOst < postNUm) {
            for (int i = numPOst; i < postNUm - 1; i++) {
                posts[i] = posts[i + 1];
            }
            posts[postNUm - 1] = null;
            postNUm--;
            for (int i = numPOst; i < comments.length - 1; i++) {
                comments[i] = comments[i + 1];
            }
            comments[comments.length - 1] = null;

            for (int i = numPOst; i < likes.length - 1; i++) {
                likes[i] = likes[i + 1];
            }
            likes[likes.length - 1] = 0;
        }
    }

    static public void confirm(String password){
        Scanner inputt = new Scanner(System.in);
        String conf = JOptionPane.showInputDialog("Confirm your password:");
        int tryy =0;
        while (!conf.equals(password)){
            conf = JOptionPane.showInputDialog("The given password is not matching, try again:");
            confirm = false;
            ++tryy;
            if (tryy == 3){
                JOptionPane.showMessageDialog(null, "You have tried too much!\nYou will be returned to the main menu!");
                return;
            }
        }
        confirm = true;
    }
    static public void capcha(){
        Scanner inputt = new Scanner(System.in);
        Random rand = new Random();
        int number1 = rand.nextInt(20);
        int number2 = rand.nextInt(20);
        int sum = number2+number1;

        String input = JOptionPane.showInputDialog(null, "Whats the sum of these two numbers?\n "+ number1 +" and "+ number2);
        int result = Integer.parseInt(input);
        if (result != sum){
            JOptionPane.showMessageDialog(null,"YOU ARE A ROBOT");
            capcha = false;
            return;
        }
    }
}
