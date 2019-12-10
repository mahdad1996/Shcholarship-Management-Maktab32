package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.UseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;

@UseCase
public interface FindScholarshipByStudentUseCase {
    Scholarship find(Long scholarshipId);
}
