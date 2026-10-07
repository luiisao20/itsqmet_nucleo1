package com.itsqmet.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.itsqmet.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
