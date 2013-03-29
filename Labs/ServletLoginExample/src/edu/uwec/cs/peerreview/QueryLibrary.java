package edu.uwec.cs.peerreview;

public class QueryLibrary {

	public static final String 
		INSTRUCTOR_NAME = "select firstname, lastname " +
				"from instructor " +
				"where instructor_id = ?",
		
		INSTRUCTOR_CLASS_NUMBERS = "select distinct class.class_id class_id, class_number " +
				"from class inner join section " +
				"on class.class_id = section.class_id " +
				"where instructor_id = ? " +
				"order by class_number",
			
		CLASSES_FROM_INSTRUCTOR_WITH_FLOATERS = 
				"select distinct class.class_id class_id, class_number " +
				"from class left join section " +
				"on class.class_id = section.class_id " +
				"where section_id is null or instructor_id = ? " +
				"order by class_number",
				
		SEMESTER_FROM_INSTRUCTOR_CLASS = 
				"select distinct semester " +
				"from section " +
				"where instructor_id = ? and class_id = ? " +
				"order by substring(semester,-2)",
				
		ALL_SEMESTERS = 
				"select distinct semester " +
				"from section " +
				"order by substring(semester,-2)",
				
		SECTION_FROM_INSTRUCTOR_CLASS_SEMESTER = 
				"select section_id, section_number, semester " +
				"from section " +
				"where instructor_id = ? and class_id = ? and semester like concat('%',?,'%') " +
				"order by substring(semester,-2), semester, section_number",
				
		ASSIGNMENT_FROM_SECTION = 
				"select assignment_id, name " +
				"from assignment " +
				"where section_id = ?",
				
		STUDENTS_FROM_SECTION = 
				"select distinct student.student_id student_id, lastname, firstname, dropped " +
				"from student inner join enrolls " +
				"on student.student_id = enrolls.student_id " +
				"where section_id = ? and dropped = 0 " +
				"order by lastname, firstname",
				
		STUDENTS_FROM_SECTION_ASSIGNMENT_SUBMISSION = 
				"select distinct student.student_id student_id, lastname, firstname, file_id " +
				"from student inner join enrolls " +
				"on student.student_id = enrolls.student_id " +
				"inner join review " +
				"on student.student_id = review.reviewee_id " +
				"where section_id = ? and dropped = 0 and assignment_id = ? and file_id is not null " +
				"order by lastname, firstname",
				
		STUDENTS_FROM_SECTION_WITH_DROPPED = 
				"select distinct student.student_id, lastname, firstname, dropped " +
				"from student inner join enrolls " +
				"on student.student_id = enrolls.student_id " +
				"where section_id = ? " +
				"order by dropped,  lastname, firstname",
				
		STUDENTS_NOT_IN_SECTION = 
				"select distinct student_id, lastname, firstname " +
				"from student " +
				"where student_id not in ( " +
					"select distinct student_id " +
					"from enrolls " +
					"where section_id = ? and dropped = 0) " +
				"order by lastname, firstname",
				
		REVIEWERS_ASSIGNED_CHECK = 
				"select count(reviewee_id) reviewer_count " +
				"from review inner join reviewer_link " +
				"on review.review_id = reviewer_link.review_id " +
				"where assignment_id = ?",
				
		SUBMISSION_ONLY_STATUS = 
				"select submitted_at is not null submitted " +
				"from review " +
				"inner join student " +
				"on review.reviewee_id = student.student_id " +
				"where assignment_id = ? " +
				"order by lastname, firstname ",
				
		SUBMISSION_REVIEW_STATUS =
				"select student.student_id student_id, isnull(submitted_at) submit_is_null, isnull(reviewed_at) review_is_null " +
				"from review inner join enrolls on enrolls.student_id = review.reviewee_id " +
				"inner join reviewer_link on reviewer_link.review_id = review.review_id " +
				"inner join student on student.student_id = review.reviewee_id " +
				"where section_id = ? and assignment_id = ? and dropped = 0 " +
				"order by lastname, firstname",
		
		//if the new way continues to work, we can safely eliminate the query above this one...
		SUBMISSION_REVIEW_STATUS2 = 
				"select student.student_id student_id, isnull(submitted_at) submit_is_null, isnull(reviewed_at) review_is_null " +
				"from review inner join enrolls on enrolls.student_id = review.reviewee_id " +
				"inner join reviewer_link on reviewer_link.review_id = review.review_id " +
				"inner join student on student.student_id = review.reviewee_id " +
				"where section_id = ? and assignment_id = ? and student.student_id = ? and dropped = 0 " +
				"order by lastname, firstname",
				
		ASSIGNMENT_INFO_FROM_ASSIGNMENT = 
				"select name, date_format(submission_due,'%b %d, %Y %h:%i %p') submission_due, " +
				"date_format(review_due,'%b %d, %Y %h:%i %p') review_due " +
				"from assignment " +
				"where assignment.assignment_id = ?",
				
		REVIEWEE_FROM_ASSIGNMENT_REVIEWEE = 
				"select distinct lastname, firstname, " +
					"date_format(submitted_at,'%b %d, %Y %h:%i %p') submitted_at,  " +
					"datediff(review.submitted_at,submission_due) days_late " +
				"from review " +
				"inner join student on student.student_id = review.reviewee_id " +
				"inner join assignment on assignment.assignment_id = review.assignment_id " +
				"where review.assignment_id = ? and reviewee_id = ?",
	
		REVIEWERS_FROM_ASSIGNMENT_REVIEWEE = 
				"select distinct lastname, firstname,  " +
					"date_format(downloaded_at,'%b %d, %Y %h:%i %p') downloaded_at, " +
					"date_format(reviewed_at,'%b %d, %Y %h:%i %p') reviewed_at, reviewer_link.reviewer_id reviewer_id " +
				"from review " +
				"inner join reviewer_link on review.review_id = reviewer_link.review_id " +
				"inner join student on student.student_id = reviewer_link.reviewer_id " +
				"where assignment_id = ? and reviewee_id = ? " +
				"order by lastname, firstname",
		
		STUDENTS_AVAILABLE_FOR_REVIEW =
				"select distinct student.student_id student_id, lastname, firstname " +
				"from student inner join enrolls on student.student_id = enrolls.student_id " +
				"left join review on student.student_id = review.reviewee_id " +
				"where section_id = ? and (not(reviewee_id = ?) or reviewee_id is null) ",
		
		REVIEW_FROM_REVIEWEE_ASSIGNMENT = 
				"select review_id " +
				"from review " +
				"where reviewee_id = ? and assignment_id = ?",
				
		REVIEWER_LINK_EXISTS = 
				"select review_id " +
				"from reviewer_link " +
				"where review_id = ? and reviewer_id = ?",
				
		DELETE_REVIEWER_LINKS_FOR_REVIEWEE_ASSIGNMENT = 
				"delete from reviewer_link " +
				"where review_id in (" +
					"select review_id " +
					"from review " +
					"where reviewee_id = ? and assignment_id = ?)",
					
		DELETE_ANSWERS_FOR_REVIEWER_LINKS = 
				"delete from answer " +
				"where reviewer_link_id in (" +
					"select reviewer_link_id " +
					"from reviewer_link " +
					"inner join review on reviewer_link.review_id = review.review_id " +
					"where reviewee_id = ? and assignment_id = ?)",
				
		INSTRUCTOR_FROM_USERNAME = 
				"select instructor_id " +
				"from instructor " +
				"where username = ?",
				
		ALL_INSTRUCTOR_USERNAME = 
				"select username " +
				"from instructor " +
				"order by username",
					
		STUDENT_FROM_USERNAME = 
				"select student_id " +
				"from student " +
				"where username = ?",
			
		ALL_STUDENT_USERNAME = 
				"select username " +
				"from student " + 
				"order by username",
					
		CLASS_INFO = 
				"select class_number, catalog_name " +
				"from class " +
				"where class_id = ?",
				
		SECTION_INFO = 
				"select semester, section_number " +
				"from section " +
				"where section_id = ?",
				
		STUDENT_INFO = 
				"select username, firstname, lastname " +
				"from student " +
				"where student_id = ?",
				
		STUDENT_EXISTS = 
				"select student_id " +
				"from student " +
				"where username = ?",
				
		STUDENT_UNDROP_ENROLL = 
				"update enrolls " +
				"set dropped = 0 " +
				"where section_id = ? and student_id = ?",
		
		FILE_DOWNLOAD_FROM_REVIEWEE_ASSIGNMENT =
				"select distinct file_blob " +
				"from review inner join file " +
				"on file.file_id = review.file_id " +
				"where reviewee_id = ? and assignment_id = ?",
				
		INSERT_ASSIGNMENT = 
				"insert into assignment(name, submission_due, review_due, section_id) " +
				"values (?,?,?,?)",
				
		INSERT_QUESTION = 
				"insert into question(question_text, question_type) " +
				"values(?,?)",
				
		INSERT_QUESTION_ASSIGNMENT_LINK = 
				"insert into assignment_question(assignment_id, question_id, question_number) " +
				"values(?, ?, ?)",
				
		INSERT_CHECKBOX =
				"insert into checkbox(question_id, answer_text, checkbox_number) " +
				"values (?,?,?)",
				
		INSERT_RADIO = 
				"insert into radio(question_id, answer_text, radio_number) " +
				"values (?,?,?)",
	
		INSERT_CLASS = 
				"insert into class(class_number, catalog_name) " +
				"values (?,?)",
				
		UPDATE_CLASS = 
				"update class " +
				"set class_number = ?, catalog_name = ? " +
				"where class_id = ?",
				
		DELETE_CLASS = 
				"delete from class " +
				"where class_id = ?",
				
		INSERT_SECTION = 
				"insert into section(class_id,instructor_id,semester,section_number) " +
				"values (?,?,?,?)",
				
		UPDATE_SECTION = 
				"update section " +
				"set semester = ?, section_number = ? " +
				"where section_id = ?",
				
		DELETE_SECTION = 
				"delete from section " +
				"where section_id = ?",
				
		INSERT_STUDENT = 
				"insert into student(username, firstname, lastname) " +
				"values (?,?,?)",
		
		INSERT_INSTRUCTOR = 
					"insert into instructor(username, firstname, lastname) " +
					"values (?,?,?)",
					
		INSERT_STUDENT_WITH_PW = 
				"insert into student(username, firstname, lastname, password) " +
				"values (?,?,?,?)",
		
		INSERT_INSTRUCTOR_WITH_PW = 
					"insert into instructor(username, firstname, lastname, password) " +
					"values (?,?,?,?)",
		
		UPDATE_STUDENT =
				"update student " +
				"set username = ?, firstname = ?, lastname = ? " +
				"where student_id = ?",
				
		UPDATE_STUDENT_PASSWORD =
				"update student " +
				"set password = ? " +
				"where student_id = ?",
				
		UPDATE_STUDENT_EMAIL =
				"update student " +
				"set email = ? " +
				"where student_id = ?",
				
		EMAIL_FROM_STUDENT_ID = "select email " +
				"from student " +
				"where student_id = ?",
			
		ENROLL_STUDENT = 
				"insert into enrolls(student_id, section_id) " +
				"values (?,?)",
				
		DROP_STUDENT = 
				"update enrolls " +
				"set dropped = 1 " +
				"where student_id = ? and section_id = ?",
				
		DELETE_ENROLLMENT = 
				"delete from enrolls " +
				"where student_id = ? and section_id = ?",
				
		STUDENT_FULLNAME = 
				"select firstname, lastname " +
				"from student " +
				"where student_id = ?",
				
		INSERT_FILE = 
				"insert into file(file_blob, file_name) values (?,?)", 
				
		UPDATE_REVIEW_SUBMISSION = 
				"update review " +
				"set submitted_at = ?, file_id = ? " +
				"where reviewee_id = ? and assignment_id = ?",
				
		DELETE_REVIEW_SUBMISSION = 
				"update review " +
				"set submitted_at = null, file_id = null " +
				"where reviewee_id = ? and assignment_id = ?",
				
		DOWNLOAD_FILE =
				"select file_blob from file where file_id = ?",
				
		FILE_INFO_FROM_REVIEWEE_ASSIGNMENT = 
				"select distinct file.file_id file_id, name, username, file_name " +
				"from review inner join assignment " +
				"on review.assignment_id = assignment.assignment_id " +
				"inner join student " +
				"on student.student_id = review.reviewee_id " +
				"inner join file on file.file_id = review.file_id " +
				"where reviewee_id = ? and review.assignment_id = ?",
				
				
		FILE_INFO_FROM_ASSIGNMENT = "SELECT r.file_id, s.username, f.file_name " +
				" FROM peerreview.review r " +
				" INNER JOIN student s " +
				" ON s.student_id = r.REVIEWEE_ID " +
				" INNER JOIN peerreview.file f " +
				" on f.file_id = r.file_id " +
				" WHERE r.assignment_id = ? and r.file_id IS NOT null;",
//				SELECT r.file_id, s.username, f.file_name
//				 FROM peerreview.review r
//				 INNER JOIN student s
//				 ON s.student_id = r.reviewee_id
//       		 INNER JOIN peerreview.file f
//        		 on f.file_id = r.file_id
//				 WHERE r.assignment_id = 17 and r.file_id IS NOT null;


		INSERT_REVIEWER_LINK =
				"insert into reviewer_link (review_id, reviewer_id) " +
				"values (?,?)",
				
		INSERT_REVIEW = 
				"insert into review(reviewee_id, assignment_id) values (?,?)",
				
		DEREFERENCE_ASSIGNMENT = 
				"update assignment " +
				"set section_id = null " +
				"where assignment_id = ?",
				
		ANSWERS_FOR_QUESTION_ASSIGNMENT_REVIEWEE =
				"select answer_id, answer_text, lastname, firstname, reviewer_id  " +
				"from answer " +
				"inner join reviewer_link on reviewer_link.reviewer_link_id = answer.reviewer_link_id " +
				"inner join review on reviewer_link.review_id = review.review_id " +
				"inner join student on student.student_id = reviewer_link.reviewer_id " +
				"where reviewee_id = ? and assignment_id = ? and question_id = ? " +
				"order by lastname, firstname",
				
		AFTER_SUBMISSION_DUE_FOR_ASSIGNMENT = 
				"select (submission_due-NOW()) < 0 is_after " +
				"from assignment " +
				"where assignment_id = ?",
				
		AFTER_REVIEW_DUE_FOR_ASSIGNMENT =
				"select (review_due-NOW()) < 0 is_after " +
				"from assignment " +
				"where assignment_id = ?",
		
		UNSUBMITTED_ASSIGNMENT =
				"select assignment.assignment_id assignment_id, review_id, reviewee_id, name, " +
				"submitted_at IS NULL is_unsubmitted " +
				"from assignment " +
				"inner join review " +
				"on review.assignment_id = assignment.assignment_id " +
				"where assignment.assignment_id = ? and reviewee_id = ?",
				
		STUDENT_CLASS_NUMBERS =
				"select distinct class.class_id, class_number " +
				"from class inner join section " +
				"on class.class_id = section.class_id " +
				"inner join enrolls " +
				"on section.section_id = enrolls.section_id " +
				"where student_id = ? and dropped = 0 " + 
				"order by class_number",

		STUDENT_ASSIGNMENTS_FROM_SECTION = 
				"select name, date_format(submission_due,'%b %d, %Y %h:%i %p') submission_due, " +
					"assignment.assignment_id assignment_id " +
				"from assignment inner join enrolls " +
				"on assignment.section_id = enrolls.section_id " +
				"inner join section " +
				"on assignment.section_id = section.section_id " +
				"inner join class " +
				"on class.class_id = section.class_id " +
				"where student_id = ? and class_number = ?",

		// STUDENT_ASSIGNMENTS_REVIEW_FROM_SECTION and STUDENT_ASSIGNMENT_REVIEW_DETAILED: 
		// It is advised that these queries be written so as to guarantee 
		// that results are returned in a consistent order (e.g., not allowing  
		// Assignment1[A] to later become Assignment1[B]). The following 
		// ORDER BY clause, "order by name, reviewer_link_id", does this.  
		// Previous ORDER BY clauses (such as "order by name" or
		// "order by name, submitted_at desc, reviewer_link_id") did not.
		
		STUDENT_ASSIGNMENTS_REVIEW_FROM_SECTION = 
				"select name, submission_due, " +
					"date_format(review_due,'%b %d, %Y %h:%i %p') review, " +
					"assignment.assignment_id assignment_id, reviewee_id " +
				"from assignment inner join section " +
				"on assignment.section_id = section.section_id " +
				"inner join class " +
				"on section.class_id = class.class_id " +
				"inner join review " +
				"on review.assignment_id = assignment.assignment_id " +
				"inner join reviewer_link " +
				"on reviewer_link.review_id = review.review_id " +
				"where reviewer_id = ? and class_number = ? " +
				"order by name, reviewer_link_id",
			
		STUDENT_ASSIGNMENTS_REVIEW_DETAILED =
				"select name, reviewer_id, class_number, submission_due, "+
					"date_format(review_due,'%b %d, %Y %h:%i %p') review, "+
					"assignment.assignment_id assignment_id, reviewee_id, "+
					"downloaded_at, reviewed_at, submitted_at "+
				"from assignment inner join section "+
				"on assignment.section_id = section.section_id "+
				"inner join class "+
				"on section.class_id = class.class_id "+
				"inner join review "+
				"on review.assignment_id = assignment.assignment_id "+
				"inner join reviewer_link "+
				"on reviewer_link.review_id = review.review_id "+
				"where reviewer_id = ? and class_number = ? " +
				"order by name, reviewer_link_id",
				
		STUDENT_ASSIGNMENTS_COUNT = 
				"select count(name) " +
				"from assignment inner join enrolls " +
				"on assignment.section_id = enrolls.section_id " +
				"inner join section " +
				"on assignment.section_id = section.section_id " +
				"inner join class " +
				"on class.class_id = section.class_id " +
				"where student_id = ? and class_number = ?",
				
		STUDENT_ASSIGNMENTS_REVIEW_COUNT = 
				"select count(reviewer_id) " +
				"from review inner join assignment " +
				"on review.assignment_id = assignment.assignment_id " +
				"inner join section " +
				"on assignment.section_id = section.section_id " +
				"inner join class " +
				"on section.class_id = class.class_id " +
				"inner join reviewer_link " +
				"on review.review_id = reviewer_link.review_id " +
				"where reviewer_id = ? and class_number = ?",
				
		CHECK_EXISTING_SUBMISSION =
				"select file_id " +
				"from review " +
				"where reviewee_id = ? and assignment_id = ?",
		
		CHECK_EXISTING_REVIEW = 
				"select count(review.review_id) review_count " +
				"from review inner join reviewer_link " +
				"on review.review_id = reviewer_link.review_id " +
				"where reviewee_id = ? and assignment_id = ? and not(reviewed_at is null)",
		
		CHECK_DOWNLOADED = 
				"select downloaded_at " +
				"from review inner join reviewer_link " +
				"on review.review_id = reviewer_link.review_id " +
				"where reviewer_id = ? and assignment_id = ? and reviewee_id = ?",
				
		UPDATE_DOWNLOADED_FROM_REVIEWER_LINK=
				"update reviewer_link " +
				"set downloaded_at = ? " +
				"where reviewer_link_id = ?",
				
		REVIEWER_LINK_FROM_REVIEWEE_ASSIGNMENT_REVIEWER = 
				"select reviewer_link_id " +
				"from review inner join reviewer_link " +
				"on review.review_id = reviewer_link.review_id " +
				"where reviewee_id = ? and assignment_id = ? and reviewer_id = ? ",
					
		CHECK_EXISTING_REVIEW_SUBMITTED = 
				"select reviewed_at " +
				"from review inner join reviewer_link " +
				"on review.review_id = reviewer_link.review_id " +
				"where reviewer_id = ? and assignment_id = ? and reviewee_id = ?",
				
		GET_REVIEW_ID = 
				"select review.review_id " +
				"from review inner join reviewer_link " +
				"on review.review_id = reviewer_link.review_id " +
				"where assignment_id = ? and reviewer_id  = ? and reviewee_id = ?",
			
		BLANK_REVIEW_INFORMATION = 
				"select question_number, question_text, question_type, question.question_id question_id " +
				"from assignment_question inner join question " +
				"on assignment_question.question_id = question.question_id " +
				"where assignment_id = ? " +
				"order by question_number",
				
		FILL_EXISTING_ANSWERS = 
			"select answer_text " +
			"from answer inner join reviewer_link " +
			"on answer.reviewer_link_id = reviewer_link.reviewer_link_id " +
			"inner join review " +
			"on review.review_id = reviewer_link.review_id " +
			"where review.review_id = ? and reviewer_id = ? and question_id = ?",
				
		FETCH_RADIO_OPTIONS = 
				"select radio_number, answer_text " +
				"from radio " +
				"where question_id = ? " +
				"order by radio_number", 
				
		FETCH_CHECKBOX_OPTIONS = 
				"select checkbox_number, answer_text " +
				"from checkbox " +
				"where question_id = ? " +
				"order by checkbox_number", 
				
		FILL_ASSIGNMENT_INFORMATION = 
				"select name, date_format(submission_due,'%m/%d/%Y %h:%i:%p') submission_due, date_format(review_due,'%m/%d/%Y %h:%i:%p') review_due " +
				"from assignment " +
				"where assignment_id = ? ", 
				
		NULL_ASSIGNMENT = 
				"update assignment " +
				"set section_id = null " +
			  	"where assignment_id = ?", 
		
		ADD_ANSWER =
				"insert into answer(reviewer_link_id, question_id, answer_text) values (?,?,?)", 
				
		OVERWRITE_ANSWER = 
				"update answer set answer_text = ? where reviewer_link_id = ? and question_id = ?",
				
		GET_REVIEWER_LINK = 
				"select reviewer_link_id " +
				"from reviewer_link " +
				"where review_id = ? and reviewer_id = ?",
		
		GET_QUESTION_ID = 
				"select question_id " +
				"from question " +
				"where question_text = ?", 
				
		TIME_STAMP_REVIEW_SUBMISSION = 
				"update reviewer_link " +
				"set reviewed_at = ? " +
				"where reviewer_link_id = ?", 
				
		CHECK_EXISTING_ANSWERS = 
				"select answer_id " +
				"from answer " +
				"where reviewer_link_id = ?",
				
		//DELETE/EDIT CALLS
		
		GET_REVIEW_IDS = 
				"select review_id " +
				"from review " +
				"where assignment_id = ?",
				
		GET_REVIEWER_LINK_IDS = 
				"select reviewer_link_id " +
				"from reviewer_link " +
				"where review_id = ?", 
		
		MAKE_REVIEWED_AT_NULL = 
				"update reviewer_link " +
				"set reviewed_at = NULL " +
				"where reviewer_link_id = ?",
				
		DELETE_ANSWERS = 
				"delete from answer " +
				"where reviewer_link_id = ?", 
				
		GET_QUESTION_IDS = 
				"select question_id " +
				"from assignment_question " +
				"where assignment_id = ?", 
				
		GET_QUESTION_TYPE = 
				"select question_type " +
				"from question " +
				"where question_id = ?", 
				
		DELETE_ASSIGNMENT_QUESTION = 
				"delete from assignment_question " +
				"where question_id = ?",
				
		DELETE_QUESTION = 
				"delete from question " +
				"where question_id = ?", 
				
		DELETE_RADIO = 
				"delete from radio " +
				"where question_id = ?",
				
		DELETE_CHECKBOX = 
				"delete from checkbox " +
				"where question_id = ?", 
				
		UPDATE_ASSIGNMENT = 
				"update assignment set name = ?, submission_due = ?, review_due = ?, section_id = ? " +
				"where assignment_id = ?", 
				
		DELETE_REVIEWER_LINKS = 
				"delete from reviewer_link " +
				"where reviewer_link_id = ?", 
		
		GET_SPECIFIC_FILE_ID = 
			"select file.file_id " +
			"from file INNER JOIN review " +
			"on file.file_id = review.file_id " +
			"where reviewee_id = ? AND assignment_id = ?",
			
		GET_FILE_IDS = 
				"select file_id " +
				"from review " +
				"where assignment_id = ?", 
				
		DELETE_REVIEWS = 
				"delete from review " +
				"where assignment_id = ?", 
				
		DELETE_FILES = 
				"delete from file " +
				"where file_id = ?", 
				
		DELETE_ASSIGNMENT = 
				"delete from assignment " +
				"where assignment_id = ?",
				
		DELETE_STUDENT_ENROLL_IN_SECTION =
			"delete from enrolls " +
			"where section_id = ?",

		LIST_UNSUBMITTED_STUDENTS_AND_EMAIL_FOR_ASSIGNMENT = 
			"select distinct student.firstname, student.lastname, student.email, student.student_id " +
			"from review inner join student " +
			"on review.reviewee_id = student.student_id " +
			"where review.assignment_id = ? " +
			"and review.submitted_at is null ",
	
		LIST_UNREVIEWED_STUDENTS_AND_EMAIL_FOR_ASSIGNMENT = 
			"select distinct student.firstname, student.lastname, student.email, student.student_id " +
			"from reviewer_link inner join student " +
			"on reviewer_link.reviewer_id = student.student_id " +
			"inner join review " +
			"on reviewer_link.review_id = review.review_id " +
			"where review.assignment_id = ? " +
			"and reviewer_link.reviewed_at is null ",
			
		INSERT_REMINDER_TEXT = 
			"insert into reminder_text(text, subject) " +
			"values (?,?)",
			
		INSERT_REMINDER = 
			"insert into reminder(time_sent, student_id, assignment_id, text_id, type_id) " +
			"values (?,?,?,?,?)";
		
}