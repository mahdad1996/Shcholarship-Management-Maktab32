package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.UseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

@UseCase
public interface FindScholarshipByUniversityUseCase {
    public List<Scholarship> findScholarships();
}
