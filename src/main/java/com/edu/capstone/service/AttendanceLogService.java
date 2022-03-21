package com.edu.capstone.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.capstone.common.constant.AppConstant;
import com.edu.capstone.entity.Account;
import com.edu.capstone.entity.AttendanceLog;
import com.edu.capstone.entity.Classs;
import com.edu.capstone.repository.AttendanceLogRepository;

/**
 * @author NhatHH Date: Feb 20, 2022
 */
@Service
public class AttendanceLogService {

	@Autowired
	private AttendanceLogRepository logRepository;
	@Autowired
	private ClassService classService;

	public List<AttendanceLog> getLogBySlotId(int slotId) {
		return logRepository.findBySlotId(slotId);
	}

	public void importStudentIntoSlotLog(int slotId, String classId) {
		Classs classs = classService.getById(classId);
		Set<Account> students = classs.getStudents();
		Iterator<Account> iterator = students.iterator();
		while (iterator.hasNext()) {
			Account student = iterator.next();
			AttendanceLog log = AttendanceLog.builder().studentId(student.getId()).slotId(slotId)
					.status(AppConstant.ATTENDANCE_NOT_YET_STATUS).build();
			logRepository.saveAndFlush(log);
		}
	}

	public void deleteLogBySlotId(int slotId) {
		logRepository.deleteBySlotId(slotId);
	}

	public void takeAttendance(String studentId, int slotId, String status) {
		AttendanceLog log = logRepository.findBySlotIdAndStudentId(slotId, studentId);
		if (log == null) {
			log = AttendanceLog.builder().studentId(studentId).slotId(slotId).status(status).build();
		} else {
			log.setStatus(status);
		}
		logRepository.saveAndFlush(log);
	}
	
	public AttendanceLog getBySlotIdAndStudentId(String studentId, int slotId) {
		return logRepository.findBySlotIdAndStudentId(slotId, studentId);
	}
	
}
