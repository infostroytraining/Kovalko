package controllers;

import domain.User;
import services.UserService;
import services.ValidatorService;
import services.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "registration", urlPatterns = "/registration")
public class RegistrationController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserService userService = (UserService) getServletContext().getAttribute("userService");
        ValidatorService validatorService = (ValidatorService) getServletContext().getAttribute("validatorService");

        HttpSession session = req.getSession();
        String captcha = (String) session.getAttribute("captcha");
        String code = req.getParameter("code");

        if (validatorService.checkCaptcha(captcha, code)) {
            User user = new User();
            user.setName(req.getParameter("name"));
            user.setLastName(req.getParameter("lastName"));
            user.setEmail(req.getParameter("email"));
            user.setPassword(req.getParameter("password"));

            try {
                if (validatorService.validateUserData(user)) {
                    //uploadFile(req, res);
                    userService.add(user);
                    res.getWriter().write("You was registered successfully!");
                } else if (!validatorService.validateUserData(user))
                    res.getWriter().write("Incorrect user data!");
            } catch (ServiceException ex) {
                res.getWriter().write("Error on server!");
            }

        } else res.getWriter().write("Incorrect captcha!");
    }

    /*public void uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);

        String uploadPath = getServletContext().getRealPath("")
                + File.separator + "images";

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);

                        item.write(storeFile);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/
}