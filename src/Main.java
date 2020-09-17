import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Main extends JFrame {
    //ALL THE STATIC VARIABLE
    private static HashMap<String, String> login_arr = new HashMap<>();
    private static ArrayList<record> record_arr = new ArrayList<>();
    private static int throwaway;

    //-------------------------------------------------------------------------------------------------
    private Main() {
        initialize();
    }

    public static void main(String[] args) {
        Main mainobj = new Main();
        mainobj.setVisible(true);

    }
    private void initialize(){
        Scanner inputStream = null;
        Scanner inputStream2 = null;
        try {

            inputStream = new Scanner(new FileInputStream("StaffUsernamePassword.txt"));
            inputStream2 = new Scanner(new FileInputStream("records.txt"));


        } catch (FileNotFoundException e) {
            System.out.println("File can't be found");
            System.exit(0);
        }


        while (inputStream.hasNext()) {
            String line = inputStream.next();
            String line2 = inputStream.next();
            login_arr.put(line,line2);
        }
        //BOOK RECORDS
        throwaway = inputStream2.nextInt(); // fix later MAYBE
        while(inputStream2.hasNext()){
            String s = inputStream2.nextLine();
            if(!s.isEmpty()) {

                String line2 = inputStream2.nextLine();
                String line3 = inputStream2.next();
                String line4 = inputStream2.next();
                String line5 = inputStream2.next();
                record_arr.add(new record(s,line2,line3,line4,line5));
            }
        }

        setTitle("LIBRARY MANAGEMENT");
        setSize(500,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel MenuPanel = new JPanel();
        add(MenuPanel);

        setLocationRelativeTo(null);
        setVisible(true);


        JLabel label = new JLabel("PLEASE CHOOSE AN OPTION BELOW",SwingConstants.CENTER);
        label.setFont(new Font("MS Gothic", Font.BOLD, 25));
        label.setForeground(Color.blue);
        MenuPanel.setLayout(new GridLayout(3,1,25,25));
        MenuPanel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        MenuPanel.setBackground(Color.GRAY);
        MenuPanel.setVisible(true);
        MenuPanel.add(label);


        JButton firstbutton = new JButton("LOGIN AS STAFF");
        firstbutton.setFont(new Font("Calibri",Font.BOLD,22));
        firstbutton.setForeground(Color.RED);
        firstbutton.addActionListener(new ActionListener() {
            // BUTTON FOR STUDENT LOGIN ------------------------------------------------------------------------
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFrame login = new JFrame("Login as Staff");
                login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                login.setVisible(true);
                login.setLocationRelativeTo(null);
                login.setSize(600,350);
                JPanel panel = new JPanel(new GridLayout(3,2,10,10));
                panel.setBackground(Color.RED);
                panel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
                //JLabel login_label = new JLabel("Please enter your credentials");
                panel.setVisible(true);
                JLabel name_label = new JLabel("Name:" );
                name_label.setFont(new Font("Calibri",Font.BOLD,25));
                name_label.setForeground(Color.WHITE);
                final JTextField name = new JTextField(20);
                JLabel pass_label = new JLabel("Password:");
                pass_label.setForeground(Color.WHITE);
                pass_label.setFont(new Font("Calibri",Font.BOLD,25));
                JLabel dummy = new JLabel();
                final JTextField pass = new JPasswordField(20);
                JButton LoginButt = new JButton("Login");
                panel.add(name_label);
                panel.add(name);
                panel.add(pass_label);
                panel.add(pass);
                panel.add(dummy);
                panel.add(LoginButt);
                login.add(panel);
                login.pack();
                login.setLocationRelativeTo(null);
                LoginButt.setFont(new Font("Calibri",Font.BOLD,25));
                LoginButt.setBackground(Color.white);
                LoginButt.setForeground(Color.BLACK);
                LoginButt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String username = name.getText();
                        String password = new String(((JPasswordField) pass).getPassword());
                        if (password.equals(login_arr.get(username))) { //create a login screen if user/password matches
                            login.setVisible(false);
                            name.setText("");
                            pass.setText("");
                            JFrame login_frame = new JFrame("Login as " + username);
                            JOptionPane.showMessageDialog(null, "Welcome " + username + "!");
                            login_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            login_frame.setVisible(true);
                            login_frame.setSize(400,350);
                            JPanel loginPanel = new JPanel(new GridLayout(6,2,15,15) );
                            loginPanel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
                            loginPanel.setBackground(Color.cyan);
                            JLabel titleL = new JLabel("Book Title");
                            JTextField titleF = new JTextField(15);
                            JLabel authorL = new JLabel("Author");
                            JTextField authorF = new JTextField(15);
                            JLabel pubL = new JLabel("Pub/Rel Date: ");
                            JTextField pubF = new JTextField(15);
                            JLabel isbnL = new JLabel("ISBN-10");
                            JTextField isbnF = new JTextField(15);
                            JLabel empty1 = new JLabel();
                            JLabel empty2 = new JLabel();
                            JButton savebutt = new JButton("Save");
                            savebutt.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String setTitle = titleF.getText();
                                    String setAuthor = authorF.getText();
                                    String setPub = pubF.getText();
                                    String setIsbn = isbnF.getText();
                                    record_arr.add(new record(setTitle,setAuthor,setPub,setIsbn,"Yes"));
                                    JOptionPane.showMessageDialog(null, "Successfully Added");
                                    throwaway++;
                                    writeFile();
                                    titleF.setText("");
                                    authorF.setText("");
                                    pubF.setText("");
                                    isbnF.setText("");
                                }
                            });
                            JButton logbutt = new JButton("Logout");
                            logbutt.setForeground(Color.white);
                            logbutt.setBackground(Color.red);
                            logbutt.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    login_frame.dispose();
                                    setVisible(true);
                                }
                            });
                            loginPanel.add(titleL);
                            loginPanel.add(titleF);
                            loginPanel.add(authorL);
                            loginPanel.add(authorF);
                            loginPanel.add(pubL);
                            loginPanel.add(pubF);
                            loginPanel.add(isbnL);
                            loginPanel.add(isbnF);
                            loginPanel.add(empty1);
                            loginPanel.add(savebutt);
                            loginPanel.add(empty2);
                            loginPanel.add(logbutt);
                            login_frame.add(loginPanel);
                            login_frame.setLocationRelativeTo(null);
                        } else {
                            JOptionPane.showMessageDialog(null, "Wrong Username or Password");
                            name.setText("");
                            pass.setText("");
                        }
                    }
                });

            }
        });


        MenuPanel.add(firstbutton);
        JButton secondbutton = new JButton("LOGIN AS STUDENT");
        secondbutton.setFont(new Font("Calibri",Font.BOLD,22));
        secondbutton.setForeground(Color.MAGENTA);
        MenuPanel.add(secondbutton);
        secondbutton.addActionListener(new ActionListener() {
            // BUTTON FOR STAFF LOGIN --------------------------------------------------------------------------
            @Override
            public void actionPerformed(ActionEvent e) {
                // For making the JTABLE
                String rowData[][] = new String[record_arr.size()][5];
                String columnNames[] = { "Book Title", "Author", "Pub/Rel Date", "ISBN-10","Check out?"};

                for(int i=0; i < record_arr.size();i++){
                    rowData[i][0] = record_arr.get(i).getTitle();
                    rowData[i][1] = record_arr.get(i).getAuthor();
                    rowData[i][2] = record_arr.get(i).getRelease_date();
                    rowData[i][3] = record_arr.get(i).getIsbn();
                    rowData[i][4] = record_arr.get(i).getCheckout();
                }
//                    for(int i = 0; i<2; i++) {
//                        for (int j = 0; j < 5; j++) {
//                            System.out.println(rowData[i][j]);
//                        }
//                    }
                // DISPLAYING THE TABLE
                setVisible(false);
                JFrame login = new JFrame("Login as Student");
                login.setBackground(Color.lightGray);
                login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                JPanel login_panel = new JPanel(new GridBagLayout());
                login_panel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
                login.setVisible(true);
                GridBagConstraints con = new GridBagConstraints();
                con.fill = GridBagConstraints.HORIZONTAL;


                JTable InfoTable = new JTable(rowData, columnNames);
                InfoTable.getColumnModel().getColumn(0).setPreferredWidth(200);
                InfoTable.getColumnModel().getColumn(1).setPreferredWidth(150);
                InfoTable.getColumnModel().getColumn(2).setPreferredWidth(125);
                InfoTable.getColumnModel().getColumn(3).setPreferredWidth(125);
                InfoTable.getColumnModel().getColumn(4).setPreferredWidth(100);


                InfoTable.setFillsViewportHeight(true);

                InfoTable.setFillsViewportHeight(true);
                JScrollPane scrollPane = new JScrollPane(InfoTable);
                scrollPane.setViewportView(InfoTable);
                con.weightx = 1;
                con.gridwidth = 2;
                con.gridx = 0;
                con.gridy = 0;
                login_panel.add(InfoTable.getTableHeader(), con);
                InfoTable.getTableHeader().setBackground(Color.BLUE);
                InfoTable.getTableHeader().setForeground(Color.white);
                InfoTable.getTableHeader().setFont(new Font("Calibri",Font.BOLD,14));
                InfoTable.setBackground(Color.CYAN);
                InfoTable.setForeground(Color.black);
                login_panel.setBackground(Color.lightGray);
                InfoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                login_panel.add(scrollPane);

                con.gridy = 1;
                con.gridx = 0;
                login_panel.add(InfoTable, con);

                con.weighty = 1.0;
                con.gridx = 1;
                con.gridy = 2;
                JLabel book_label = new JLabel("Click on the book you wish to borrow");
                login_panel.add(book_label, con);

                con.weighty = 1.0;
                con.gridx = 1;
                con.gridy = 3;
                JLabel book_label1 = new JLabel("NOTICE: If the check out status is No, the book is not available");
                login_panel.add(book_label1, con);

                con.weighty = 1.0;
                con.gridwidth = 1;
                con.gridx = 2;
                con.gridy = 2;
                JButton checkoutbutt = new JButton("Borrow");
                checkoutbutt.setBackground(Color.YELLOW);
                checkoutbutt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        if(record_arr.get(InfoTable.getSelectedRow()).getCheckout().equals("Yes") && InfoTable.getSelectedRow() != -1) {
                            record_arr.get(InfoTable.getSelectedRow()).setCheckout("No");
                            JOptionPane.showMessageDialog(null, "Successfully Borrowed");
                        }

                        else if(record_arr.get(InfoTable.getSelectedRow()).getCheckout().equals("No") && InfoTable.getSelectedRow() != -1) {
                            JOptionPane.showMessageDialog(null, "The Book is not available!");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Please select a book");

                        }
                        InfoTable.revalidate();
                    }
                });
                login_panel.add(checkoutbutt, con);
                con.weighty = 1.0;
                con.gridx = 2;
                con.gridy = 3;
                JButton returnButt = new JButton("Return");
                returnButt.setBackground(Color.MAGENTA);
                returnButt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(record_arr.get(InfoTable.getSelectedRow()).getCheckout().equals("Yes") && InfoTable.getSelectedRow() != -1) {
                            JOptionPane.showMessageDialog(null, "This Book hasn't been borrowed");
                        }

                        else if(record_arr.get(InfoTable.getSelectedRow()).getCheckout().equals("No") && InfoTable.getSelectedRow() != -1) {
                            record_arr.get(InfoTable.getSelectedRow()).setCheckout("Yes");
                            JOptionPane.showMessageDialog(null, "Successfully returned!");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Please select a book");

                        }
                        InfoTable.revalidate();
                    }
                });

                login_panel.add(returnButt, con);

                con.weighty = 1.0;
                con.gridx = 2;
                con.gridy = 4;
                JButton logoutbutt = new JButton("Log Out");
                logoutbutt.setBackground(Color.red);
                logoutbutt.setForeground(Color.WHITE);
                logoutbutt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        login.dispose();
                        writeFile();
                        setVisible(true);
                    }
                });

                login_panel.add(logoutbutt,con);
                login.add(login_panel);
                login.pack();
                login.setLocationRelativeTo(null);


            }
        });
    }

    private void writeFile() {
        try {
            BufferedWriter writeRecord = new BufferedWriter(new FileWriter("records.txt"));
            writeRecord.write(String.valueOf(throwaway));
            writeRecord.newLine();
            for(int i = 0; i < record_arr.size(); i++) {
                String titleW = record_arr.get(i).getTitle();
                String authorW = record_arr.get(i).getAuthor();
                String releaseW = record_arr.get(i).getRelease_date();
                String isbnW = record_arr.get(i).getIsbn();
                String checkoutW = record_arr.get(i).getCheckout();
                writeRecord.write(titleW + "\n");
                writeRecord.write(authorW + "\n");
                writeRecord.write(releaseW + "\n");
                writeRecord.write(isbnW + "\n");
                writeRecord.write(checkoutW + "\n");
                writeRecord.newLine();
            }
            writeRecord.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File not found");
        }
    }
}
