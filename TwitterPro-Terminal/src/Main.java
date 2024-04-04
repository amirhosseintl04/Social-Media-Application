import javax.swing.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static int len = 0;
    public static void main(String[] args) {
        User[] user = new User[300];
        Scanner inputt = new Scanner(System.in);
        String input;
        do
        {
            System.out.print( "\nWelcome to the program, please select a choice!\n1)Sign up\n2)Log in\n3)Exit\n> ");
            input = inputt.next();
            while (!Objects.equals(input, "1") && !Objects.equals(input, "2") && !Objects.equals(input, "3"))
            {
                System.out.print( "Your choice is not among the options!\n1)Sign up\n2)Log in\n3)Exit\n> ");
                input = inputt.next();
            }

            if (input.equals("1"))
            {
                user[len] = new User();
                User.signUp(user[len],user);
            }
            else if (input.equals("2"))
            {
                if(len==0){
                    System.out.println("You have not created an account!\nJoin today.");
                }else {
                    User.login(user);
                }
            }
        } while (!input.equals("3"));
    }
}