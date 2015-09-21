package com.matchprize.batch.common.listeners;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;

public class CommonJobListener implements JobExecutionListener{
	
	 private String jobName;
	
	 public CommonJobListener(String jobName) {
		this.jobName = jobName;
	}

	@BeforeJob
		public void beforeJob(JobExecution jobExecution) {
		 System.out.println("//////////////" + this.jobName + "Job start////////////////");
	 }
	 
	 @AfterJob
	 public void afterJob(JobExecution jobExecution){
		  if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			  System.out.println("//////////////" + this.jobName + "Job Done///////////////////");
		  }
	 }
	  

}
