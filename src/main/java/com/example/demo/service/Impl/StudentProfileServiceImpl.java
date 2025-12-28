@Service
@Transactional
public class StudentProfileServiceImpl
implements StudentProfileService {

private final StudentProfileRepository studentRepo;
private final IntegrityCaseRepository integrityCaseRepo;

public StudentProfileServiceImpl(
StudentProfileRepository studentRepo,
IntegrityCaseRepository integrityCaseRepo,
RepeatOffenderRecordRepository repeatRepo,
RepeatOffenderCalculator calculator
) {
this.studentRepo = studentRepo;
this.integrityCaseRepo = integrityCaseRepo;
}

@Override
public StudentProfile createStudent(StudentProfile student) {
student.setRepeatOffender(false);
return studentRepo.save(student);
}

@Override
public StudentProfile getStudentById(Long id) {
return studentRepo.findById(id)
.orElseThrow(() -> new ResourceNotFoundException("Student not found"));
}

@Override
public List<StudentProfile> getAllStudents() {
return studentRepo.findAll();
}

@Override
public StudentProfile updateRepeatOffenderStatus(Long studentId) {

StudentProfile student = getStudentById(studentId);

List<IntegrityCase> cases =
integrityCaseRepo.findByStudentProfile(student);

student.setRepeatOffender(cases.size() >= 2);

return studentRepo.save(student);
}
}
