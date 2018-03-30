package com.fkapi.datebase.dao;

import com.fkapi.datebase.domain.ex_profession;

public interface ex_professionMapper {

    ex_profession selectByCollegeNameAndProfessionName(ex_profession record);

}