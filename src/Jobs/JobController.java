package Jobs;

import java.util.ArrayList;

/**
 * Created by MariusDK on 01.05.2017.
 */
public class JobController {
    ArrayList<Job> list=new ArrayList<>();
    JobsProvider provider=new JobsProvider();
    public ArrayList getJob()
    {
        list=provider.getJobs();
        return list;
    }
    public int idJob()
    {
        int id_job=0;
        if (list.size()==0)
        {
            return 1;
        }
        else
        {
            for (Job o:list)
            {
                if (o.getId()>id_job)
                {
                    id_job=o.getId();
                }
            }
        }
        return id_job;
    }
    public void addJob(String name,String min_salary,String number,int id_documnet)
    {

        int id_job=idJob();
        Job j=new Job(id_job,name,min_salary,number,id_documnet);
        provider.insertJob(j);
        list.add(j);
    }
    public void editJob(int id_job,String name,String min_salary,String number,int id_document)
    {
        Job j=new Job(id_job,name,min_salary,number,id_document);
        provider.updateJob(j);
        int nr=0;
        for (Job o:list)
        {
            if (o.getId()!=j.getId())
            {
                nr++;
            }
        }
        list.remove(nr);
        list.add(nr,j);
    }
    public void removeJob(Job j)
    {
        list.remove(j);
        provider.deleteJob(j);
    }

}
