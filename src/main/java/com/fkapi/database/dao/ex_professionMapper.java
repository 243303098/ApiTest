package com.fkapi.database.dao;

import com.fkapi.database.domain.ex_profession;

public interface ex_professionMapper {

    ex_profession selectByCollegeNameAndProfessionName(ex_profession record);

}