public interface IntegrityCaseRepository
extends JpaRepository<IntegrityCase, Long> {

List<IntegrityCase> findByStudentProfile(StudentProfile studentProfile);

long countByStudentProfile_Id(Long studentId);

List<IntegrityCase> findByStudentProfile_Id(Long studentId);

@Query("""
select c from IntegrityCase c
where c.studentProfile.studentId = :studentIdentifier
""")
List<IntegrityCase> findByStudentIdentifier(
@Param("studentIdentifier") String studentIdentifier
);

List<IntegrityCase> findByStatus(String status);

@Query("""
select c from IntegrityCase c
where c.status = :status
and c.incidentDate >= :sinceDate
""")
List<IntegrityCase> findRecentCasesByStatus(
@Param("status") String status,
@Param("sinceDate") LocalDate sinceDate
);

List<IntegrityCase> findByIncidentDateBetween(
LocalDate start,
LocalDate end
);
}
