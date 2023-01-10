package com.popeye.backend.entity;

import com.popeye.backend.pdf.PDFcreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
public class Program {
    public List<ProgramSession> program;

    public Program(List<ProgramSession> program) {
        this.program = program;
    }

    PDFcreator currentPdf;
}
