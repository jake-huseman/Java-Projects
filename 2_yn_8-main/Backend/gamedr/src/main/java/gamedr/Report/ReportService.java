package gamedr.Report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gamedr.Moderator.Moderator;
import gamedr.Rejection.Rejection;
import gamedr.Users.User;
import gamedr.Users.UserRepository;

/**
 * This class services the Report class, an intermediary between the Report Entity and the Report Repository.
 * @author David Dong
 *
 */
@Service
public class ReportService {
	
	@Autowired
	private ReportRepository reportRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	// <----- GET SERVICES ----->

	/**
	 * Gets Report by User id
	 * @param id
	 * @return reportRepository.findByUser_Id(id);
	 */
	public Report getReportByUserId(int id) {
		return reportRepository.findByUser_Id(id);
	}

	/**
	 * Gets all Reports in the Report Repository.
	 * @return reportRepository.findAll();
	 */
	public List<Report> getAllReports() {
		return reportRepository.findAll();
	}

	/**
	 * Gets all Reported Users
	 * @return ret
	 */
	public List<User> getAllReportedUsers() {
		List<User> ret = new ArrayList<User>();
		for (Report report : reportRepository.findAll()) {
			User reportedUser = report.getUser();
			ret.add(reportedUser);
		}
		return ret;
	}
	
	// <----- POST SERVICES ----->

	/**
	 * Saves the Report.
	 * @param user
	 * @param report
	 */
	public void postReportAutoSave(User user, Report report) {
		reportRepository.save(report);
		user.setReport(report);
		userRepository.save(user);
	}
	
	// <----- PUT SERVICES ----->


	/**
	 * Updates Report Reason.
	 * @param user
	 * @param moderator
	 * @param reason
	 */
	public void putReportReason(User user, Moderator moderator, String reason) {
		Report currentReport = user.getReport();
		Report newReport = new Report(currentReport.getUser(), reason);
		postReportAutoSave(user, newReport);
	}
	
}
