package com.popeye.backend.entity;

import com.popeye.backend.services.PDFGeneratorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Program {
    public List<ProgramSession> program;
}
