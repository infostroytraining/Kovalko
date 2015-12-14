package controllers;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@WebServlet(name = "CaptchaServlet", urlPatterns = "/captcha/CaptchaServlet")
public class CaptchaServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {

        int width = 150;
        int height = 50;

        char data[][] = {
                { 'l', 'i', 'n', 'u', 'x' },
                { 'c', 'o', 'd', 'i', 'n', 'g' },
                { 'f', 'r', 'e', 'e', 'b', 's', 'd' },
                { 'u', 'b', 'u', 'n', 't', 'u' },
                { 'j', 'a', 'v', 'a' },
                { 'p', 'r', 'o', 'g', 'r', 'a', 'm' },
                { 'j', 'e', 't', 't', 'y' },
                { 's', 'u', 'p', 'e', 'r', 'm', 'a', 'n' },
                { 's', 'e', 'r', 'v', 'l', 'e', 't' },
                { 'p', 'y', 't', 'h', 'o', 'n' },
        };


        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = bufferedImage.createGraphics();

        Font font = new Font("Georgia", Font.BOLD, 18);
        g2d.setFont(font);

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        GradientPaint gp = new GradientPaint(0, 0,
                Color.red, 0, height/2, Color.black, true);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(new Color(255, 153, 0));

        Random r = new Random();
        int index = Math.abs(r.nextInt()) % 7;

        String captcha = String.copyValueOf(data[index]);
        request.getSession().setAttribute("captcha", captcha );

        int x = 0;
        int y = 0;

        for (int i=0; i<data[index].length; i++) {
            x += 10 + (Math.abs(r.nextInt()) % 15);
            y = 20 + Math.abs(r.nextInt()) % 20;
            g2d.drawChars(data[index], i, 1, x, y);
        }

        g2d.dispose();

        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(bufferedImage, "png", os);
        os.close();
    }


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}