/*
    Document   : eBooksStoreAdmineBooksServlet.java
    Created on : Dec 22, 2018, 5:03:39 PM
    Author     : Ciprian Alexandru Lucian Brebu
 */
package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gheor
 */
public class eBooksStoreAdminEBooks extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // declare specific DB info
        String user = "test";
        String password = "test";
        String url = "jdbc:derby://localhost:1527/ebooksstore;create=true;";
        String driver = "org.apache.derby.jdbc.ClientDriver";
        // check push on Insert button
        if (request.getParameter("admin_ebooks_insert") != null) { // insert values from fields
            // set connection paramters to the DB
            // read values from page fields

            String isbn = request.getParameter("admin_ebooks_isbn");
            String title = request.getParameter("admin_ebooks_title");
            String id_type = request.getParameter("admin_ebooks_id_type");
            String id_quality = request.getParameter("admin_ebooks_id_paper_qualities");
            String pages = request.getParameter("admin_ebooks_pages");
            String id_genre = request.getParameter("admin_ebooks_id_genres");
            String price = request.getParameter("admin_ebooks_price");

            // declare specific DBMS operationsvariables
            ResultSet resultSet = null;
            Statement statement = null;
            Connection connection = null;
            PreparedStatement pstmnt = null;
            try {
                //check driver and create connection
                Class driverClass = Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                if (!("".equals(isbn))) {
                    String DML = "INSERT INTO EBOOKS.EBOOKS VALUES (?, ?, ?, ?, ?, ?, ?)";
                    pstmnt = connection.prepareStatement(DML);
                    pstmnt.setString(1, isbn);
                    pstmnt.setString(2, title);
                    pstmnt.setInt(3, Integer.parseInt(id_type));
                    pstmnt.setInt(4, Integer.parseInt(id_quality));
                    pstmnt.setInt(5, Integer.parseInt(pages));
                    pstmnt.setInt(6, Integer.parseInt(id_genre));
                    pstmnt.setDouble(7, Double.parseDouble(price));

                    pstmnt.execute();
                }
            } catch (ClassNotFoundException | SQLException ex) {
                // display a message for NOT OK
                Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (pstmnt != null) {
                    try {
                        pstmnt.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // redirect page to its JSP as view
                request.getRequestDispatcher("./eBooksStoreAdminEBooks.jsp").forward(request, response);
            }
        } // check push on Update button
        else if (request.getParameter("admin_ebooks_update") != null) { // update
            // declare specific variables
            ResultSet resultSet = null;
            Statement statement = null;
            PreparedStatement pstmnt = null;
            Connection connection = null;
            try {
                //check driver and create connection
                Class driverClass = Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                // identify selected checkbox and for each execute the update operation
                String[] selectedCheckboxes = request.getParameterValues("admin_ebooks_checkbox");
                String title = request.getParameter("admin_ebooks_title");
                String type = request.getParameter("admin_ebooks_id_type");
                String quality = request.getParameter("admin_ebooks_id_paper_qualities");
                String pages = request.getParameter("admin_ebooks_pages");
                String genre = request.getParameter("admin_ebooks_id_genres");
                String price = request.getParameter("admin_ebooks_price");

                // if both username and password are "" do nothing
                if (!(("".equals(title)) && ("".equals(pages)) && ("".equals(price)) && ("".equals(quality)) && ("".equals(genre)) && ("".equals(type)))) {
                    // operate updates for all selected rows
                    for (String s : selectedCheckboxes) {
                        // realize update of all selected rows
                        String isbn = s;
                        if (!"".equals(title)) { // only password/s should be updated
                            String DML = "UPDATE EBOOKS.EBOOKS SET isbn=?, title=? WHERE ISBN=?";
                            pstmnt = connection.prepareStatement(DML);
                            pstmnt.setString(1, isbn);
                            pstmnt.setString(2, title);
                            pstmnt.setString(3, isbn);
                            boolean execute = pstmnt.execute();
                        }
                        if (!"".equals(pages)) { // only password/s should be updated
                            String DML = "UPDATE EBOOKS.EBOOKS SET isbn=?, pages=? WHERE ISBN=?";
                            pstmnt = connection.prepareStatement(DML);
                            pstmnt.setString(1, isbn);
                            pstmnt.setString(2, pages);
                            pstmnt.setString(3, isbn);
                            boolean execute = pstmnt.execute();
                        }
                        if (!"".equals(price)) { // only password/s should be updated
                            String DML = "UPDATE EBOOKS.EBOOKS SET isbn=?, price=? WHERE ISBN=?";
                            pstmnt = connection.prepareStatement(DML);
                            pstmnt.setString(1, isbn);
                            pstmnt.setString(2, price);
                            pstmnt.setString(3, isbn);
                            boolean execute = pstmnt.execute();
                        }
                        if (!"".equals(type)) { // only password/s should be updated
                            String DML = "UPDATE EBOOKS.EBOOKS SET isbn=?, id_type=? WHERE ISBN=?";
                            pstmnt = connection.prepareStatement(DML);
                            pstmnt.setString(1, isbn);
                            pstmnt.setInt(2, Integer.parseInt(type));
                            pstmnt.setString(3, isbn);
                            boolean execute = pstmnt.execute();
                        }
                        if (!"".equals(quality)) { // only password/s should be updated
                            String DML = "UPDATE EBOOKS.EBOOKS SET isbn=?, id_quality=? WHERE ISBN=?";
                            pstmnt = connection.prepareStatement(DML);
                            pstmnt.setString(1, isbn);
                            pstmnt.setInt(2, Integer.parseInt(quality));
                            pstmnt.setString(3, isbn);
                            boolean execute = pstmnt.execute();
                        }
                        if (!"".equals(genre)) { // only password/s should be updated
                            String DML = "UPDATE EBOOKS.EBOOKS SET isbn=?, id_genre=? WHERE ISBN=?";
                            pstmnt = connection.prepareStatement(DML);
                            pstmnt.setString(1, isbn);
                            pstmnt.setInt(2, Integer.parseInt(genre));
                            pstmnt.setString(3, isbn);
                            boolean execute = pstmnt.execute();
                        }

                    }
                }

            } catch (ClassNotFoundException | SQLException ex) {
                // display a message for NOT OK
                Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);

            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (pstmnt != null) {
                    try {
                        pstmnt.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // redirect page to its JSP as view
                request.getRequestDispatcher("./eBooksStoreAdminEBooks.jsp").forward(request, response);
            }
        } // check push on Delete button
        else if (request.getParameter("admin_ebooks_delete") != null) { // delete 
            // declare specific variables
            ResultSet resultSet = null;
            PreparedStatement pstmnt = null;
            Connection connection = null;
            try {
                //check driver and create connection
                Class driverClass = Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                // identify selected checkbox and for each execute the delete operation
                String[] selectedCheckboxes = request.getParameterValues("admin_ebooks_checkbox");
                // more critical DB operations should be made into a transaction
                connection.setAutoCommit(false);
                for (String s : selectedCheckboxes) {
                    // realize delete of all selected rows
                    String ssn = s;
                    String DML = "DELETE FROM EBOOKS.EBOOKS WHERE ISBN=?";
                    pstmnt = connection.prepareStatement(DML);
                    pstmnt.setString(1, ssn);
                    pstmnt.execute();
                }
                connection.commit();
                connection.setAutoCommit(true);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex1) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (pstmnt != null) {
                    try {
                        pstmnt.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (pstmnt != null) {
                    try {
                        pstmnt.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (connection != null) {
                    try {
                        connection.setAutoCommit(true);
                    } catch (SQLException ex) {
                        Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(eBooksStoreAdminEBooks.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                // redirect page to its JSP as view
                request.getRequestDispatcher("./eBooksStoreAdminEBooks.jsp").forward(request, response);
            }
        } // check push on Cancel button
        else if (request.getParameter("admin_ebooks_cancel") != null) { // cancel
            request.getRequestDispatcher("./eBooksStoreMainPage.jsp").forward(request, response);
        } // check push on admin user roles button
        /*else if (request.getParameter("admin_userroles_open") != null) { // insert values from fields
                request.getRequestDispatcher("./eBooksStoreAdminUserRolesPage.jsp").forward(request, response);
            } // check push on admin customers button           */
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet serves eBooksSoreAdminEBooks.JSP page";
    }// </editor-fold>

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

}
