package Jobs;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by MariusDK on 01.05.2017.
 */

public class JobsController extends Observable {

    ArrayList<Job> list = new ArrayList();
    JobsProvider provider = new JobsProvider();

    public ArrayList getJobs() {
        list = provider.getJobs();
        return list;
    }

    public int idJob() {
        int id_job=0;
        if (list.size()==0) {
            return 1;
        }
        else {
            for (Job o:list) {
                if (o.getId()>id_job) {
                    id_job=o.getId();
                }
            }
            id_job++;
        }
        return id_job;
    }

    public void addJob(String name,String min_salary,String max_number,int id_documnet) {
        int id_job = idJob();
        int Max_number = Integer.parseInt(max_number);
        Job j = new Job(id_job, name, min_salary, Max_number, id_documnet);
        provider.insertJob(j);
        list.add(j);
        setChanged();
        notifyObservers();
    }

    public void editJob(int id_job,String name,String min_salary,String max_number,int id_document) {
        int Max_number=Integer.getInteger(max_number);
        Job j=new Job(id_job, name, min_salary, Max_number, id_document);
        provider.updateJob(j);
        int nr=0;
        for (Job o:list) {
            if (o.getId()!=j.getId())
            {
                nr++;
            }
        }
        list.remove(nr);
        list.add(nr,j);
        setChanged();
        notifyObservers();
    }

    public void removeJob(Job j) {
        list.remove(j);
        provider.deleteJob(j);
        setChanged();
        notifyObservers();
    }
}
