package com.keyin.rest.division;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DivisionService {
    @Autowired
    private DivisionRepository divisionRepository;

    public Division findByName(String name) {
        return divisionRepository.findByName(name).orElse(null);
    }

    public Division findByStartBirthYear(String startBirthYear) {
        return divisionRepository.findByStartBirthYear(startBirthYear).orElse(null);
    }

    public List<Division> getAllDivisions() {
        return (List<Division>) divisionRepository.findAll();
    }

    public Division getDivisionById(long id) {
        return divisionRepository.findById(id).orElse(null);
    }

    public void deleteDivisionById(long id) {
        divisionRepository.deleteById(id);
    }

    public Division createDivision(Division newDivision) {
        return divisionRepository.save(newDivision);
    }

    public Division updateDivision(long id, Division updatedDivision) {
        Optional<Division> divisionToUpdateOptional = divisionRepository.findById(id);

        if (divisionToUpdateOptional.isPresent()) {
            Division divisionToUpdate = divisionToUpdateOptional.get();
            divisionToUpdate.setName(updatedDivision.getName());
            divisionToUpdate.setStartBirthYear(updatedDivision.getStartBirthYear());
            divisionToUpdate.setEndBirthYear(updatedDivision.getEndBirthYear());
            return divisionRepository.save(divisionToUpdate);
        }

        return null;
    }
}
