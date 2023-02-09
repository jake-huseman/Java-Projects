package gamedr.Report;

import java.util.List;

import gamedr.Rejection.Rejection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gamedr.Matchmaker.Matchmaker;
import gamedr.Matchmaker.MatchmakerRepository;
import gamedr.Moderator.Moderator;
import gamedr.Moderator.ModeratorRepository;
import gamedr.Users.User;
import gamedr.Users.UserRepository;
import gamedr.Users.UserService;

/**
 * This class controls the Report class.
 * @author David Dong
 *
 */

@Api(value = "ReportController", tags = "report-controller")
@RestController
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private ReportRepository reportRepository;
	
	@Autowired
	private ModeratorRepository moderatorRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;

	/**
	 * String to show a Users actions were successful.
	 */
    private String success = "{\"message\":\"success\"}";

	/**
	 * String to show a Users actions were not successful.
	 */
    private String failure = "{\"message\":\"failure\"}";
	
	// <----- GET MAPPINGS ----->

	/**
	 * Gets all Reports.
	 * @return List of Reports
	 */
	@ApiOperation(value = "Get all Reports from the database", response = Report.class, tags = "report-controller")
	@GetMapping(path = "")
	public List<Report> getAllReports() {
		return reportService.getAllReports();
	}

	/**
	 * Gets Report by id.
	 * @param report_id
	 * @return report
	 */
	@ApiOperation(value = "Get Report by Report ID from the database", response = Report.class, tags = "report-controller")
	@GetMapping(path = "/{report_id}")
	public Report getReportById(@PathVariable int report_id) {
		return reportRepository.findById(report_id);
	}

//	/**
//	 * Gets Reports by Severity.
//	 * @param severity
//	 * @return List of Reports
//	 */
//	@ApiOperation(value = "Get all Reports ordered by Severity", response = Report.class, tags = "report-controller")
//	@GetMapping(path = "/severity/{severity}")
//	public List<Report> getAllReportsBySeverity(@PathVariable Severity severity) {
//		return reportService.getAllReportsBySeverity(severity);
//	}

	/**
	 * Gets all Reported Users.
	 * @return List of Users
	 */
	@ApiOperation(value = "Get all Reported Users, excluding the Report object itself", response = Report.class, tags = "report-controller")
	@GetMapping(path = "/user")
	public List<User> getAllReportedUsers() {
		return reportService.getAllReportedUsers();
	}
//
//	/**
//	 * Gets all Users by Report Severity.
//	 * @param severity
//	 * @return List of Users
//	 */
//	@ApiOperation(value = "Get all Reported Users By Severity from the database, excluding the Report objects themselves", response = Report.class, tags = "report-controller")
//	@GetMapping(path = "/user/severity/{severity}")
//	public List<User> getAllReportedUsersBySeverity(@PathVariable Severity severity) {
//		return reportService.getAllReportedUsersBySeverity(severity);
//	}

	/**
	 * Gets Reports by User id.
	 * @param user_id
	 * @return Report
	 */
	@GetMapping(path = "/user/{user_id}")
	@ApiOperation(value = "Get all Reports by User ID", response = Report.class, tags = "report-controller")
	public Report getReportByUserId(@PathVariable int user_id) {
		return reportService.getReportByUserId(user_id);
	}
	
	// <----- POST MAPPINGS ----->

	/**
	 * Create a Report.
	 * @param user_id
	 * @param moderator_id
	 * @param severity
	 * @param reason
	 * @return success if saved, failure if null
	 */
	@ApiOperation(value = "Create a Report by HTTP Request (not JSON)", response = String.class, tags = "report-controller")
	@PostMapping(path = "")
	public String postReport(
			@RequestParam String username,
			@RequestParam String reason
	) {
		if (!userService.usernameExists(username))
			return failure;
		User user = userRepository.findByUsername(username);
		Report report = new Report(user, reason);
		reportService.postReportAutoSave(user, report);
		return success;
	}
	
	@PostMapping(path = "ban/{username}")
	public String banUser(@PathVariable String username) {
		if (!userService.usernameExists(username)) 
			return failure;
		User user = userRepository.findByUsername(username);
		user.setBanned();
		userRepository.save(user);
		
		return success;
	}
	
	// <----- PUT MAPPINGS ----->
//
//	/**
//	 * Updates a Reports Severity.
//	 * @param user_id
//	 * @param moderator_id
//	 * @param severity
//	 * @return success if updated, failure if null
//	 */
//	@ApiOperation(value = "Update severity of a Report object by Report ID through HTTP Request", response = Report.class, tags = "report-controller")
//	@PutMapping(path = "/severity")
//	public String putSeverity(
//			@RequestParam int user_id, 
//			@RequestParam("moderator_id") @ApiParam(name = "moderator_id", value = "ID of Moderator submitting report") int moderator_id,
//			@RequestParam Severity severity
//	) {
//		if (severity == null)
//			return failure;
//		User user = userRepository.findById(user_id);
//		Moderator moderator = moderatorRepository.findById(moderator_id);
//		if (user.getReport() == null)
//			return "User has not been reported";
//		reportService.putReportSeverity(user, , severity);
//		return success;
//	}

	/**
	 * Updates
	 * @param user_id
	 * @param moderator_id
	 * @param reason
	 * @return success if updated, failure if null
	 */
	@ApiOperation(value = "Update reason of report through HTTP request", response = Report.class, tags = "report-controller")
	@PutMapping(path = "/reason")
	public String putSeverity(
			@RequestParam int user_id, 
			@RequestParam("moderator_id") @ApiParam(name = "moderator_id", value = "ID of Moderator submitting report") int moderator_id,
			@RequestParam String reason
	) {
		if (reason == null)
			return failure;
		User user = userRepository.findById(user_id);
		Moderator moderator = moderatorRepository.findById(moderator_id);
		if (user.getReport() == null)
			return "User has not been reported";
		reportService.putReportReason(user, moderator, reason);
		return success;
	}
	
}
