package edu.uwec.cs.peerreview;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.Window;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import com.mysql.jdbc.Blob;
import com.mysql.jdbc.PacketTooBigException;
import javax.swing.JFrame;


public class DBUtils {

	private final static int DATA_BLOCK_SIZE = 2048;
	public static boolean cancelled = false; //if the user cancels out of uploading a submission
	public static String currentDirectory = ""; //keep track of users last uploaded from/to directory
	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//connect to mysql database using username, password and setting max_allowed_packet so that we can upload/download large blobs
			Connection cn = DriverManager.getConnection(
//					"jdbc:mysql://dario.cs.uwec.edu/PeerReviewTest?user=PeerReviewTest&password=H7wed92$&max_allowed_packet=33553408");
					"jdbc:mysql://dario.cs.uwec.edu/PeerReview?user=PeerReview&password=Prs_1324&max_allowed_packet=33553408");
			
			//initialize the override variable that will be passed around so that other parts of the application can override functions for debugging purposes
			cn.setClientInfo("override","no");
			
			return cn;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void initDatabase(Connection cn) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("MyDDL.txt"));
			ArrayList<String> queries = new ArrayList<String>();
			StringBuffer buf = new StringBuffer();
			int c = br.read();
			while (c != -1) {
				char ch = (char)c;
				buf.append(ch);
				if (ch == ';') {
					queries.add(buf.toString());
					buf = new StringBuffer();
				}
				c = br.read();
			}
			PreparedStatement ps;
			for (String s: queries) {
				System.out.println(s);
				ps = cn.prepareStatement(s);
				ps.executeUpdate();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int lastInsertID(Connection cn) {
		try {
			ResultSet rs = cn.prepareStatement("select LAST_INSERT_ID()").executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
//TODO: this is used by the Swing portion but it depends on classes being named StudentView and InstructorView
//	public static boolean downloadSubmission(Window parent,Connection cn, int reviewee_id, int assignment_id, int reviewer_id, String assignment_name)  {
//		try {
//			PreparedStatement ps = cn.prepareStatement(QueryLibrary.FILE_INFO_FROM_REVIEWEE_ASSIGNMENT);
//			//reviewee_id:
//			ps.setInt(1, reviewee_id);
//			ps.setInt(2,assignment_id);
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) { //if the file was found in the database
//				int file_id = rs.getInt("file_id");
//				String filename = rs.getString("file_name");
//				
//				JFileChooser fc = new JFileChooser();
//				if (!currentDirectory.equals("")) {
//					fc.setCurrentDirectory(new File(currentDirectory));
//				}
//				fc.setDialogTitle("Download");
//				fc.setAcceptAllFileFilterUsed(false);
//				fc.setDialogType(JFileChooser.SAVE_DIALOG);
//				fc.setFileFilter(new FileFilter() { //only allow directories, so they can select where to save it
//					public boolean accept(File pathname) {
//						if (pathname.isDirectory()) {
//							return true;
//						} else {
//							return false;
//						}
//					}
//					public String getDescription() {
//						return "Directories";
//					}
//				});
//				if (filename != null) {
//					if (reviewer_id == -1) { //let it remain as original filename
//						fc.setSelectedFile(new File(filename));
//					} else { //if another peer is downloading it, give it a unique name
//						String extension = filename.substring(filename.indexOf("."));
//						assignment_name = assignment_name.replaceAll("[\t \\[\\]]", "");
//						fc.setSelectedFile(new File(assignment_name + extension));
//					}
//					
//				}
//				int returnVal = fc.showDialog(parent, "Save");
//				if (returnVal == JFileChooser.APPROVE_OPTION) {
//					File file = fc.getSelectedFile();
//					currentDirectory = file.getPath();
//					boolean okayToWrite = false;
//					if (file.exists()) {
//						int answer = JOptionPane.showConfirmDialog(parent, "File already exists. Do you want to overwrite it?", "Overwrite",JOptionPane.YES_NO_OPTION);
//						if (answer == JOptionPane.YES_OPTION) {
//							okayToWrite = true;
//						} else {
//							//let them try again
//							downloadSubmission(parent,cn,reviewee_id,assignment_id, reviewer_id, assignment_name);
//							return true; //return because the recursive call will already have handled downloading it
//						}
//					} else {
//						okayToWrite = file.createNewFile();
//					}
//					
//					if (okayToWrite) {
//						Cursor normalCursor = new Cursor(Cursor.WAIT_CURSOR);
//						((JFrame)parent).setCursor(normalCursor);
//						DBUtils.downloadFile(cn, file_id, file);
//						normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
//						((JFrame)parent).setCursor(normalCursor);
//						//as long as everything has gone smoothly, update database with time of download
//						if (parent instanceof StudentView && reviewer_id != -1) {
//							ps = cn.prepareStatement(QueryLibrary.REVIEWER_LINK_FROM_REVIEWEE_ASSIGNMENT_REVIEWER);
//							ps.setInt(1, reviewee_id);
//							ps.setInt(2, assignment_id);
//							ps.setInt(3, reviewer_id);
//							rs = ps.executeQuery();
//							
//							if (rs.next()) { //if there is a result, then the reviewer was found
//								int reviewer_link_id = rs.getInt("reviewer_link_id");
//								
//								Calendar cal = Calendar.getInstance();
//								SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//								String downloaded_at = formatter.format(cal.getTime());
//								
//								ps = cn.prepareStatement(QueryLibrary.UPDATE_DOWNLOADED_FROM_REVIEWER_LINK);
//								ps.setString(1, downloaded_at);
//								ps.setInt(2, reviewer_link_id);
//								ps.executeUpdate();
//								return true;
//							} else {
//								JOptionPane.showMessageDialog(parent, 
//											"There was an error marking the time of download.\nSee an administator for assistance.",
//											"Database Error", JOptionPane.ERROR_MESSAGE);
//							}
//						} else if (parent instanceof InstructorView) {
//							return true;
//						}
//					}
//				}
//			} else { //didn't find file (which means they must not have submitted it)
//				JOptionPane.showMessageDialog(parent, "Unable to find file. This might mean it was not submitted or the student dropped the course.","Missing File",JOptionPane.WARNING_MESSAGE);
//			}
//		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(parent, "There was an error locating the file to be downloaded.","Database Error",JOptionPane.ERROR_MESSAGE);
//			e.printStackTrace();
//			//downloadSubmission(reviewee_id,assignment_id);
//		} catch (IOException e) {
//			JOptionPane.showMessageDialog(parent, "There was an error saving the file.","File Error",JOptionPane.ERROR_MESSAGE);
//			e.printStackTrace();
//			//downloadSubmission(reviewee_id,assignment_id);
//		}
//		//if it makes it all the way down to here, then there was either a caught error or one of the if's wasn't true
//		//(which means something went wrong, so not a success
//		return false;
//	}
	
	
	public static boolean exportAll(Window parent, Connection cn,
			int assignment_id, String assignment_name) {
		// this is used if a professor wants to export all assignments for a
		// given class
		// again, this is *assignments* not *reviews*

		// declare variables once
		JFileChooser fc;
		File overall_dir = new File(".");
		int returnVal;
		int file_id;
		String username;
		String file_name;
		File temp_file;

		try {
			PreparedStatement ps = cn
					.prepareStatement(QueryLibrary.FILE_INFO_FROM_ASSIGNMENT);
			// reviewee_id:
			ps.setInt(1, assignment_id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) { // if any file was found in the database
				// make an overall folder

				fc = new JFileChooser();

				if (!currentDirectory.equals("")) {
					fc.setCurrentDirectory(new File(currentDirectory));
				} else {
					fc.setCurrentDirectory(new File("."));
				}
				fc.setDialogTitle("Download");
				fc.setAcceptAllFileFilterUsed(false);
				fc.setDialogType(JFileChooser.SAVE_DIALOG);
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setAcceptAllFileFilterUsed(false);

				fc.setFileFilter(new FileFilter() { // only allow directories,
													// so they can select where
													// to save it
							public boolean accept(File pathname) {
								if (pathname.isDirectory()) {
									return true;
								} else {
									return false;
								}
							}

							public String getDescription() {
								return "Directories";
							}
						});

				returnVal = fc.showDialog(parent, "Save");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					currentDirectory = file.getPath();

					overall_dir = new File(currentDirectory + "\\"
							+ assignment_name);

					try {
						if (overall_dir.mkdir()) {
							System.out.println("Directory "
									+ overall_dir.getName()
									+ " created -- currentDirectory is "
									+ currentDirectory);
						} else {
							System.out.println("Directory "
									+ overall_dir.getName()
									+ " is not created -- currentDirectory is "
									+ currentDirectory);
						}
					} catch (RuntimeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				

				// make a subfolder with file/zip for each file found
				do {
					
				file_id = rs.getInt("file_id");
				file_name = rs.getString("file_name");
				username = rs.getString("username");
				
				temp_file = new File(overall_dir.getPath() + "\\" + username);
				
				try {
					if (temp_file.mkdir()) {
						System.out.println("Directory "
								+ temp_file.getName()
								+ " created -- currentDirectory is "
								+ currentDirectory);
					} else {
						System.out.println("Directory "
								+ temp_file.getName()
								+ " is not created -- currentDirectory is "
								+ currentDirectory);
					}
				} catch (RuntimeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//now put the file into the folder
				
				temp_file = new File(temp_file.getPath() + "\\" + file_name);
				
				boolean okayToWrite = false;
				if (temp_file.exists()) {
					int answer = JOptionPane.showConfirmDialog(parent, "File " + file_name + " already exists. Do you want to overwrite it?", "Overwrite",JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						okayToWrite = true;
					} else {
						break;
					}
				} else {
					okayToWrite = temp_file.createNewFile();
				}
				
				if (okayToWrite) {
					Cursor normalCursor = new Cursor(Cursor.WAIT_CURSOR);
					((JFrame)parent).setCursor(normalCursor);
					DBUtils.downloadFile(cn, file_id, temp_file);
					normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
					((JFrame)parent).setCursor(normalCursor);
				}

				} while (rs.next());
				
				} else {
					System.out.println("No Selection");
				}
				
				Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
				((JFrame)parent).setCursor(normalCursor);
				return true;
			} else { // didn't find file (which means they must not have
						// submitted it)
				JOptionPane
						.showMessageDialog(
								parent,
								"Unable to find files. This might mean no files were submitted. \nCheck back later or contact your instructor.",
								"Missing File", JOptionPane.WARNING_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(parent,
					"There was an error locating the files to be downloaded.",
					"Database Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			// downloadSubmission(reviewee_id,assignment_id);
			 } catch (IOException e) {
			 JOptionPane.showMessageDialog(parent, "There was an error saving the files.","File Error",JOptionPane.ERROR_MESSAGE);
			 e.printStackTrace();
			// downloadSubmission(reviewee_id,assignment_id);
		}
		// if it makes it all the way down to here, then there was either a
		// caught error or one of the if's wasn't true
		//(which means something went wrong, so not a success
		return false;
	}
	
	
	public static boolean uploadSubmission(Component parent, Connection cn, int reviewee_id, IDItem assignment) {
		try {
			cancelled = false;
			//have user select file/directory to be uploaded
			JFileChooser fc = new JFileChooser();
			if (!currentDirectory.equals("")) {
				fc.setCurrentDirectory(new File(currentDirectory));
			}
			fc.setDialogTitle("Choose the file or directory to be submitted for " + assignment.name);
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fc.setDialogType(JFileChooser.OPEN_DIALOG);
			
			int choice = fc.showDialog(parent, "Submit");
			if (choice == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				currentDirectory = file.getPath();
				int file_id = -1;
				Cursor normalCursor = new Cursor(Cursor.WAIT_CURSOR);
				((JFrame)parent).setCursor(normalCursor);
				if (file.isDirectory()) {
					
					Long file_size = DirectoryLength(file);
					if (file_size == 0) {
						JOptionPane.showMessageDialog(parent, "File submission is empty");
						normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
						((JFrame)parent).setCursor(normalCursor);
						return false;
					} else if (file_size < 8000000){
						file_id = zipDirectoryToDB(cn, file, assignment, reviewee_id);
						normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
						((JFrame)parent).setCursor(normalCursor);
						return uploadSubmissiontoDB(parent,cn,reviewee_id, assignment, file_id);
					} else {
						JOptionPane.showMessageDialog(parent, "File submission is too large");
						normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
						((JFrame)parent).setCursor(normalCursor);
						return false;
					}
				} else {
					//TODO fix this method, files becoming corrupt
					//holtbg: It looks to me like the insert is going fine. When I submit using PeerReview, I can successfully retrieve the files using MySQL Browser.
					//		 something is getting added to the end of the file when we download something. In a plain text file it is a line or two of text from a couple
					//		 lines up, but in a more complex file, like .docx, etc, this corrupts the file, causing ms office to freak out, rightfully so.
					//		  	What I don't know is why this would cause a problem with files and not zip archives. Maybe the zip just ignores the extra pieces of the file 
					//			that we're adding?
					Long file_size = file.length();
					if (file_size == 0) {
						JOptionPane.showMessageDialog(parent, "File submission is empty");
						normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
						((JFrame)parent).setCursor(normalCursor);
						return false;
					} else if(file_size < 8000000) {
						file_id = insertFile(cn, file, assignment, reviewee_id);
						normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
						((JFrame)parent).setCursor(normalCursor);
						return uploadSubmissiontoDB(parent,cn,reviewee_id, assignment, file_id);
					} else {
						JOptionPane.showMessageDialog(parent, "File submission is too large");
						normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
						((JFrame)parent).setCursor(normalCursor);
						return false;
					}
				}
			} else if (choice == JFileChooser.CANCEL_OPTION) {
				cancelled = true;
				return false;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
//			
//			JOptionPane.showMessageDialog(null,"There was an error uploading your submission. \nThe file size may have been too large. Make sure \nyou are only selecting the folder to be submitted.",
//						"Packet Too Big Exception", JOptionPane.ERROR_MESSAGE);
//				//if those were both successful (didn't throw exceptions) then we will update the review record
//				PreparedStatement ps = cn.prepareStatement(QueryLibrary.UPDATE_REVIEW_SUBMISSION);
//				Calendar cal = Calendar.getInstance();
//				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				String submitted_at = formatter.format(cal.getTime());
//				ps.setString(1, submitted_at);
//				ps.setInt(2, file_id);
//				ps.setInt(3, reviewee_id);
//				ps.setInt(4, assignment.id);
//				int rows_updated = ps.executeUpdate();
//				if (rows_updated == 0) { //if no rows are updated, then there wasn't a valid Review record 
//					//(this shouldn't happen unless someone was tampering with the database by hand)
//					//insert needed (missing) review record
//					ps = cn.prepareStatement(QueryLibrary.INSERT_REVIEW);
//					ps.setInt(1, reviewee_id);
//					ps.setInt(2, assignment.id);
//					ps.executeUpdate();
//					//update submission timestamp, etc
//					ps = cn.prepareStatement(QueryLibrary.UPDATE_REVIEW_SUBMISSION);
//					ps.setString(1, submitted_at);
//					ps.setInt(2, file_id);
//					ps.setInt(3, reviewee_id);
//					ps.setInt(4, assignment.id);
//					ps.executeUpdate();
//					
//					/*JOptionPane.showMessageDialog(parent, 
//							"Unable to find a suitable Review record. Check with your instructor/administrator.", 
//							"Database Error", JOptionPane.ERROR_MESSAGE);*/
//				} else { //if it made it this far in the try, and updated at least one row, then it was successful
//					return true;
//				}
//			}
//		} catch (HeadlessException e) {
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			
//			JOptionPane.showMessageDialog(null,"There was an error uploading your submission. \nThe file size may have been too large. Make sure \nyou are only selecting the folder to be submitted.",
//						"Packet Too Big Exception", JOptionPane.ERROR_MESSAGE);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		//if it makes it here, something was unsuccessful
//		return false;
	}
	public static boolean uploadSubmissiontoDB(Component parent, Connection cn, int reviewee_id, IDItem assignment, int file_id) {
		try {
				//if those were both successful (didn't throw exceptions) then we will update the review record
				PreparedStatement ps = cn.prepareStatement(QueryLibrary.UPDATE_REVIEW_SUBMISSION);
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String submitted_at = formatter.format(cal.getTime());
				ps.setString(1, submitted_at);
				ps.setInt(2, file_id);
				ps.setInt(3, reviewee_id);
				ps.setInt(4, assignment.id);
				int rows_updated = ps.executeUpdate();
				if (rows_updated == 0) { //assignment was created when student was not enrolled (reviews created during assignment process)
					ps = cn.prepareStatement(QueryLibrary.INSERT_REVIEW);
					ps.setInt(1, reviewee_id);
					ps.setInt(2, assignment.id);
					ps.executeUpdate();
					//update submission timestamp, etc
					ps = cn.prepareStatement(QueryLibrary.UPDATE_REVIEW_SUBMISSION);
					ps.setString(1, submitted_at);
					ps.setInt(2, file_id);
					ps.setInt(3, reviewee_id);
					ps.setInt(4, assignment.id);
					ps.executeUpdate();
					return true;
				} else { //if it made it this far in the try, and updated at least one row, then it was successful
					return true;
				}
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			
			JOptionPane.showMessageDialog(null,"There was an error uploading your submission. \nThe file size may have been too large. Make sure \nyou are only selecting the folder to be submitted.",
						"Packet Too Big Exception", JOptionPane.ERROR_MESSAGE);
		}
		//if it makes it here, something was unsuccessful (exceptions handled)
		return false;
	}
	
	public static long DirectoryLength(File dir) {
		if (!dir.isDirectory()) {
			return dir.length();
		} else {
			long count = 0;
			String[] list = dir.list();
			if (list == null) {
				//this means we have an empty directory
				return 0;
			} 
			for (int i = 0; i < list.length; i ++) {
				long newLength = DirectoryLength(new File(dir.toString() + "\\" + list[i]));
				count = count + newLength;
				if (count > 8000000) {
					return count;
				}
			}
			return count;
		}
		
	}
	public static int zipDirectoryToDB(Connection cn, File dir, IDItem assignment, int reviewee_id) throws SQLException, IOException, FileNotFoundException, PacketTooBigException {
		
		ByteArrayOutputStream blobOut = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(blobOut);
		
		zipFiles(dir, zos);
		zos.finish();
		ByteArrayInputStream in = new ByteArrayInputStream(blobOut.toByteArray());
		BufferedInputStream in2 = new BufferedInputStream(in);
		
		PreparedStatement ps = cn.prepareStatement(QueryLibrary.INSERT_FILE);
		
		ps.setBinaryStream(1, in2);
		//ps.setObject(1,blobOut.toByteArray());
		//ps.setString(2, assignment.name + ".zip");
		ps.setString(2, dir.getName() + ".zip");
		ps.executeUpdate();
		return lastInsertID(cn);
	}
	
	public static int insertFile(Connection cn, File f, IDItem assignment, int reviewee_id) throws SQLException, IOException, FileNotFoundException {

		if (f.exists()) {
			
			FileInputStream fis = new FileInputStream(f);
			BufferedInputStream buffIn = new BufferedInputStream(fis);
			//byte[] tmp = new byte[1024];
			//byte[] data = new byte[0];
			//int sz = 0;
			
			//Blob blob = cn.createBlob();
			//OutputStream blobOut = blob.setBinaryStream(0);
			ByteArrayOutputStream blobOut = new ByteArrayOutputStream();
			//BufferedOutputStream buffBlob = new BufferedOutputStream(blobOut);
			//long pos = 0;
			
			while (buffIn.available() > 0) {
				blobOut.write(buffIn.read());
				//buffBlob.write(buffIn.read());
			}
			
			/*while ((sz = fis.read(tmp)) != -1) {
				//sz is the num of bytes read into the buffer
				buffBlob.
				
				pos += sz;
				byte[] tmp2 = data;
				data = new byte[tmp2.length + sz];
				System.arraycopy(tmp2, 0, data, 0, tmp2.length);
				System.arraycopy(tmp, 0, data, tmp2.length, sz);
			}*/
			
			//int fileExtensionSpot = f.getName().lastIndexOf("."); //where the file extension is so we can save that
			//String NewFileName = assignment.name + f.getName().substring(fileExtensionSpot); //this saves the file using our own made-up name, for simplicity sake for the instructor when they're downloading a ton of them
			
			//check to see if they already have a file associated with the assignment_ID and reviewee_ID
			//if there is a record, run an update (return the file_id) (yes/no option)
			PreparedStatement ps = cn.prepareStatement(QueryLibrary.INSERT_FILE);
			ps.setBytes(1, blobOut.toByteArray());
			//ps.setObject(1, blobOut.toByteArray());
			ps.setString(2, f.getName());
			ps.executeUpdate();
			
			fis.close();
		}
		
		return lastInsertID(cn);
}
	
	public static void downloadFile(Connection cn, int file_id, File file) throws SQLException, IOException {
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			@SuppressWarnings("unused")
			String now = sdf.format(cal.getTime());
				
			
			PreparedStatement ps = cn.prepareStatement(QueryLibrary.DOWNLOAD_FILE);
			ps.setInt(1, file_id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next() && file.exists()) {
				Blob b = (Blob) rs.getBlob("file_blob");
				
				//this is now fixed so that it correctly reads in the blob and saves it directly to file
				//Buffering the streams is very very important with larger (500K plus) files
				
				//note: "getBinaryStream" is actually implemented by connector as a "ByteArrayInputStream", not a real stream, so it is dependent 
				//  on the size of user's memory. Nothing we can do about it, though.
				InputStream in = b.getBinaryStream();
				BufferedInputStream bIn = new BufferedInputStream(in);
				OutputStream out = new FileOutputStream(file);
				BufferedOutputStream bOut = new BufferedOutputStream(out);
				int c;
				while ((c = bIn.read()) > -1) {
					bOut.write(c);
				}
				bIn.close();
				in.close();
				bOut.close();
				out.close();
			}
			
			 cal = Calendar.getInstance();
			 sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 now = sdf.format(cal.getTime());
	}
	
	public static boolean isAfterDueDate(Connection cn, int assignment_id) {

		try {
			PreparedStatement ps = cn.prepareStatement(QueryLibrary.AFTER_SUBMISSION_DUE_FOR_ASSIGNMENT);
			ps.setInt(1, assignment_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean("is_after")) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean isAfterReviewDueDate(Connection cn, int assignment_id) {

		try {
			PreparedStatement ps = cn.prepareStatement(QueryLibrary.AFTER_REVIEW_DUE_FOR_ASSIGNMENT);
			ps.setInt(1, assignment_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean("is_after")) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean wasNeverSubmitted(Connection cn, int assignment_id, int reviewee_id) {
		try {
			PreparedStatement ps = cn.prepareStatement(QueryLibrary.UNSUBMITTED_ASSIGNMENT);
			ps.setInt(1, assignment_id);
			ps.setInt(2, reviewee_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getBoolean("is_unsubmitted")) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * zips directory and all it's contents (other version of this method is used for recursion)
	 */
	public static void zipFiles(File file, ZipOutputStream zos) {
		zipFiles(file,zos,file);
	}
	private static void zipFiles(File file, ZipOutputStream zos, File root) {
		FileInputStream fis;
		int byteCount;
		
		try {
			if (file.isDirectory()){
				/*if(file.getName().equalsIgnoreCase(".metadata")){
					return;
				}*/
				File[] fList = file.listFiles();
				for (int i=0; i<fList.length; i++) {
					zipFiles(fList[i],zos,root);
				}
			} else {
				try {
					String rootPath = root.getPath();
					//must include the parent folder in the path name to be able to unzip it
					String path = root.getName() + file.getPath().substring(rootPath.length());
					//System.out.println("Zipping: " + path);
					
					fis = new FileInputStream(file);
					ZipEntry cpZipEntry = new ZipEntry(path);				
					zos.putNextEntry(cpZipEntry);
					
					byte[] b = new byte[DATA_BLOCK_SIZE];
					while((byteCount = fis.read(b, 0, DATA_BLOCK_SIZE)) != -1){
						zos.write(b, 0, byteCount);
					}			
					zos.closeEntry();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * WORKS!!  unzips a zipped file and saves to same directory
	 * this isn't being put to use in the current version, user has to unzip themselves, but it will be left in here for possible later use
	 */
	public static void decompressZip(File zipFile) {
		try {
			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream(zipFile);
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
			ZipEntry entry;
			while((entry = zis.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					File file = new File(entry.getName());
					file.mkdirs();
				} else {
					int count;
					byte[] data = new byte[2048];
					
					
					String filepath = entry.getName();
					System.out.println(filepath);
					
					File dir = new File(filepath.substring(0, filepath.lastIndexOf("/")+1));
					if (!dir.exists()) {
						dir.mkdirs();
					}
					File file = new File(entry.getName());
					file.createNewFile();
					FileOutputStream fos = new FileOutputStream(file);
					dest = new BufferedOutputStream(fos, 2048);
					while ((count = zis.read(data, 0, 2048)) != -1) {
						dest.write(data, 0, count);
					}
					dest.flush();
					dest.close();
				}
			}
			zis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
