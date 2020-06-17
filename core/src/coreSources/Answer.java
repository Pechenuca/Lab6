package coreSources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Answer implements Serializable {
    private String message;
    private ArrayList<Organization> organizationArrayList;

    public Answer(String message, ArrayList<Organization> organizationArrayList) {
        this.message = message;
        this.organizationArrayList = organizationArrayList;
    }

    public Answer(String message) {
        this.message = message;
    }

    public ArrayList<Organization> getOrganizationArrayList() {
        return organizationArrayList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOrganizationArrayList(ArrayList<Organization> organizationArrayList) {
        this.organizationArrayList = organizationArrayList;
    }

    @Override
    public String toString() {
        StringBuilder answer = new StringBuilder();

        if(organizationArrayList != null) {
            for(Organization organization : organizationArrayList) {
                answer.append(organization.toString()).append("\n");
            }
        }
        return
                "coreSources.Answer: \n" + message + "\n" +answer;
    }

    public void sort() {
        if(organizationArrayList!=null && organizationArrayList.size()>0) {
            Comparator<Organization> organizationComparator = new OrganizationComparator();
            organizationArrayList.sort(organizationComparator);
        }
    }
}
