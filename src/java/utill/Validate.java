/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utill;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author admin
 */
public class Validate
{

        public  String getFieldAjax(HttpServletRequest request, String fieldName, boolean required, String error) throws Exception
        {
                String value = null;
                value = request.getParameter(fieldName);
                if (value == null || value.trim().isEmpty())
                {
                        if (required)
                        {
                                throw new Exception(error);
                        } 
                        else
                        {
                                value = null; // Make empty string null so that you don't need to hassle with equals("") afterwards.
                        }
                }
                return value;
        }
}
