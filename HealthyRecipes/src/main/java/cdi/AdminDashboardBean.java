package cdi;

import Entity.ActivityLog;
import Entity.Comments;
import Entity.Recipes;
import Entity.Users;
import ejb.AdminBeanLocal;
import ejb.UserBeanLocal;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import jakarta.inject.Inject;
import java.util.Date;
import java.util.Calendar;

@Named
@SessionScoped
public class AdminDashboardBean implements Serializable {

    @Inject
    private LoginBean loginBean;
    @EJB
    private AdminBeanLocal adminBean;

    @EJB
    private UserBeanLocal userBean;

    private int userPage = 0;
    private int recipePage = 0;
    private final int pageSize = 10;
    // existing fields...
    private Integer commentFilterUserId = 0;

    // 🔽 NEW FIELDS FOR ACTIVITY LOGS
    private String activityFilter = "ALL"; // ALL, TODAY, WEEK, MONTH
    private Integer filterUserId; // For specific user
    private String searchTerm;    // For keyword search
    private int logPageIndex = 0; // 0-based index
    private final int logPageSize = 20; // you chose 20 per page

    public List<Users> getAllUsersPaginated() {
        List<Users> all = userBean.getAllUsers();
        int start = userPage * pageSize;
        int end = Math.min(start + pageSize, all.size());
        return all.subList(start, end);
    }

    public List<Recipes> getAllRecipesPaginated() {
        List<Recipes> all = userBean.getAllRecipes();
        int start = recipePage * pageSize;
        int end = Math.min(start + pageSize, all.size());
        return all.subList(start, end);
    }

    public void nextUserPage() {
        if ((userPage + 1) * pageSize < userBean.getAllUsers().size()) {
            userPage++;
        }
    }

    public void previousUserPage() {
        if (userPage > 0) {
            userPage--;
        }
    }

    public void nextRecipePage() {
        if ((recipePage + 1) * pageSize < userBean.getAllRecipes().size()) {
            recipePage++;
        }
    }

    public void previousRecipePage() {
        if (recipePage > 0) {
            recipePage--;
        }
    }

    public int getUserPage() {
        return userPage + 1;
    }

    public int getRecipePage() {
        return recipePage + 1;
    }

    public String deleteRecipe(Integer recipeId) {
        Recipes recipe = userBean.getRecipeById(recipeId);

        if (recipe != null) {
            adminBean.logActivity(loginBean.getLoggedUser(),
                    "Deleted recipe: " + recipe.getTitle());
        }

        userBean.deleteRecipe(recipeId);
        return "/admin/adminDashboard?faces-redirect=true";
    }

    public int getTotalUsers() {
        return userBean.getAllUsers().size();
    }

    public int getTotalRecipes() {
        return userBean.getAllRecipes().size();
    }

 public String toggleUserRole(Integer userId) {

    Users target = userBean.getUserById(userId);

    // ❌ Block changing role of main admin
    if (target != null && target.getUserName().equalsIgnoreCase("admin")) {

        FacesContext.getCurrentInstance().addMessage(
            null,
            new FacesMessage(
                FacesMessage.SEVERITY_ERROR,
                "You cannot change the main admin's role!",
                null
            )
        );

        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "/admin/manageUsers?faces-redirect=true";
    }

    // ❌ Only the main admin can change roles
    if (!loginBean.getLoggedUser().getUserName().equalsIgnoreCase("admin")) {

        FacesContext.getCurrentInstance().addMessage(
            null,
            new FacesMessage(
                FacesMessage.SEVERITY_ERROR,
                "Only the main admin can change user roles!",
                null
            )
        );

        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "/admin/manageUsers?faces-redirect=true";
    }

    // ❌ Prevent changing own role
    if (loginBean.getLoggedUser().getUserId().equals(userId)) {

        FacesContext.getCurrentInstance().addMessage(
            null,
            new FacesMessage(
                FacesMessage.SEVERITY_WARN,
                "You cannot change your own role!",
                null
            )
        );

        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "/admin/manageUsers?faces-redirect=true";
    }

    // ✔ Continue with role change logic
    if (target != null) {
        if ("admin".equalsIgnoreCase(target.getRole())) {
            target.setRole("USER");
        } else {
            target.setRole("ADMIN");
        }

        userBean.updateUser(target);

        adminBean.logActivity(loginBean.getLoggedUser(),
                "Changed role of: " + target.getFullName() + " to " + target.getRole());
    }

    FacesContext.getCurrentInstance().addMessage(
        null,
        new FacesMessage(
            FacesMessage.SEVERITY_INFO,
            "User role updated!",
            null
        )
    );

    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

    return "/admin/manageUsers?faces-redirect=true";
}

    public List<ActivityLog> getAllActivities() {

        if (!loginBean.getLoggedUser().getUserName().equalsIgnoreCase("admin")) {
            return List.of(); // return empty list for non-admin username
        }

        return adminBean.getAllActivities();
    }

    public List<ActivityLog> getAllActivitiesPaginated() {
        List<ActivityLog> filtered = getFilteredActivities();

        int start = logPageIndex * logPageSize;
        if (start >= filtered.size()) {
            logPageIndex = 0;
            start = 0;
        }
        int end = Math.min(start + logPageSize, filtered.size());

        System.out.println("Activities filtered count: " + filtered.size());
        return filtered.subList(start, end);
    }

    public void nextLogPage() {
        if ((logPageIndex + 1) * logPageSize < getFilteredActivities().size()) {
            logPageIndex++;
        }
    }

    public void previousLogPage() {
        if (logPageIndex > 0) {
            logPageIndex--;
        }
    }

    public String getActivityFilter() {
        return activityFilter;
    }

    public void setActivityFilter(String activityFilter) {
        this.activityFilter = activityFilter;
    }

    public Integer getFilterUserId() {
        return filterUserId;
    }

    public void setFilterUserId(Integer filterUserId) {
        this.filterUserId = filterUserId;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    // page number for UI (1-based)
    public int getLogPageNumber() {
        return logPageIndex + 1;
    }

    private List<ActivityLog> getFilteredActivities() {
        List<ActivityLog> logs = adminBean.getAllActivities(); // already DESC

        // Filter by time
        Date now = new Date();
        logs.removeIf(a -> {
            Date ts = a.getTimestamp();
            switch (activityFilter) {
                case "TODAY":
                    return !isToday(ts, now);
                case "WEEK":
                    return !isLast7Days(ts, now);
                case "MONTH":
                    return !isThisMonth(ts, now);
                default:
                    return false; // ALL
            }
        });

        // Filter by User
        if (filterUserId != null && filterUserId != 0) {
            logs.removeIf(a -> !a.getUserId().getUserId().equals(filterUserId));
        }

        // Search by keyword (activity text or user name)
        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            String keyword = searchTerm.toLowerCase();
            logs.removeIf(a
                    -> (a.getActivity() == null || !a.getActivity().toLowerCase().contains(keyword))
                    && (a.getUserId() == null || a.getUserId().getFullName() == null
                    || !a.getUserId().getFullName().toLowerCase().contains(keyword))
            );
        }

        return logs;
    }

    public int getLogTotalPages() {
        int total = getFilteredActivities().size();
        return total == 0 ? 1 : (int) Math.ceil(total / (double) logPageSize);
    }

    public void resetLogPage() {
        logPageIndex = 0;
    }

    private boolean isToday(Date date, Date now) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(now);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }

    private boolean isLast7Days(Date date, Date now) {
        long diff = now.getTime() - date.getTime();
        long days = diff / (1000L * 60 * 60 * 24);
        return days >= 0 && days < 7;
    }

    private boolean isThisMonth(Date date, Date now) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(now);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH);
    }

    public List<Users> getAllUsers() {
        return userBean.getAllUsers();
    }

    public void downloadLogs() {
        try {
            List<ActivityLog> logs = getFilteredActivities();

            StringBuilder csv = new StringBuilder();
            csv.append("User,Activity,Timestamp\n");

            for (ActivityLog log : logs) {
                csv.append(log.getUserId().getFullName()).append(",");
                csv.append("\"").append(log.getActivity().replace("\"", "\"\"")).append("\",");
                csv.append(log.getTimestamp()).append("\n");
            }

            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().setResponseContentType("text/csv");
            facesContext.getExternalContext().setResponseHeader(
                    "Content-Disposition", "attachment; filename=activity_logs.csv"
            );

            facesContext.getExternalContext()
                    .getResponseOutputWriter()
                    .write(csv.toString());

            facesContext.responseComplete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadLogsExcel() {
        try {
            List<ActivityLog> logs = getFilteredActivities();

            org.apache.poi.ss.usermodel.Workbook workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook();
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Activity Logs");

            // HEADER STYLE
            org.apache.poi.ss.usermodel.CellStyle headerStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerStyle.setFont(headerFont);

            // BORDER STYLE
            org.apache.poi.ss.usermodel.CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setBorderBottom(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            cellStyle.setBorderTop(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            cellStyle.setBorderLeft(org.apache.poi.ss.usermodel.BorderStyle.THIN);
            cellStyle.setBorderRight(org.apache.poi.ss.usermodel.BorderStyle.THIN);

            // HEADER ROW
            org.apache.poi.ss.usermodel.Row header = sheet.createRow(0);
            String[] columns = {"User", "Activity", "Timestamp"};
            for (int i = 0; i < columns.length; i++) {
                org.apache.poi.ss.usermodel.Cell cell = header.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerStyle);
            }

            // DATA ROWS
            int rowIndex = 1;
            for (ActivityLog log : logs) {
                org.apache.poi.ss.usermodel.Row row = sheet.createRow(rowIndex++);

                row.createCell(0).setCellValue(log.getUserId().getFullName());
                row.createCell(1).setCellValue(log.getActivity());
                row.createCell(2).setCellValue(log.getTimestamp().toString());

                for (int i = 0; i < 3; i++) {
                    row.getCell(i).setCellStyle(cellStyle);
                }
            }

            // AUTO SIZE COLUMNS
            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }

            // SEND FILE AS DOWNLOAD
            FacesContext fc = FacesContext.getCurrentInstance();
            var ec = fc.getExternalContext();
            ec.setResponseContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            ec.setResponseHeader("Content-Disposition", "attachment; filename=ActivityLogs.xlsx");

            workbook.write(ec.getResponseOutputStream());
            workbook.close();
            fc.responseComplete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getCommentFilterUserId() {
        return commentFilterUserId;
    }

    public void setCommentFilterUserId(Integer commentFilterUserId) {
        this.commentFilterUserId = commentFilterUserId;
    }

    public List<Comments> getFilteredComments() {
        List<Comments> list = userBean.getAllComments();

        if (commentFilterUserId != null && commentFilterUserId != 0) {
            list.removeIf(c -> !c.getUserId().getUserId().equals(commentFilterUserId));
        }
        return list;
    }

    public List<Comments> getFilteredCommentsPaginated() {
        return getFilteredComments();
    }

    public String deleteComment(int commentId) {
        userBean.deleteComment(commentId);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Comment deleted!", null));

        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        return "/admin/manageUsers?faces-redirect=true";
    }

public String deleteUser(Integer userId) {

    Users target = userBean.getUserById(userId);

    // ❌ Block deleting main admin username
    if (target != null && target.getUserName().equalsIgnoreCase("admin")) {

        FacesContext.getCurrentInstance().addMessage(
            null,
            new FacesMessage(
                FacesMessage.SEVERITY_ERROR,
                "You cannot delete the main admin account!",
                null
            )
        );

        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "/admin/manageUsers?faces-redirect=true";
    }

    // ✔ Log deletion
    adminBean.logActivity(loginBean.getLoggedUser(),
            "Deleted user: " + target.getFullName());

    // ✔ Delete user
    userBean.deleteUser(userId);

    return "/admin/manageUsers?faces-redirect=true";
}


}
