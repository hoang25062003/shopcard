/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import utill.CaptchaRen;


@WebServlet(name="captcha", urlPatterns={"/captcha"})
public class captcha extends HttpServlet {
   
     public static final String FILE_TYPE = "jpeg";

       
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException
        {
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);
                response.setHeader("Progma", "no-cache");
                response.setDateHeader("Max-Age", 0);
                
                CaptchaRen captchaRen = new CaptchaRen();
                String captcha = captchaRen.generateCaptcha(5); /// tạo ra catcha có 5 kí tự
                BufferedImage bufferedImage = captchaRen.generatePic(captcha); // tạo ra ảnh có catcha 5 kí tự

                HttpSession session = request.getSession(true);
                session.setAttribute("captcha", captcha);
                OutputStream outputStream = response.getOutputStream();
                ImageIO.write(bufferedImage, FILE_TYPE, outputStream);
                outputStream.close();
        }

       
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException
        {
             
        }

       
        @Override
        public String getServletInfo()
        {
                return "Short description";
        }// </editor-fold>

}
