package com.example.backendmd6.repository;

import com.example.backendmd6.model.ProfileEnterprise;
import com.example.backendmd6.model.Recruitment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    //    Iterable<Recruitment>findAllByAddressContaining(String title);
    @Query("SELECT r FROM Recruitment r WHERE CONCAT(r.title, ' ', r.address, ' ', r.salary) LIKE %?1%")
    Page<Recruitment> search(String key, Pageable pageable);

    @Query("SELECT r FROM Recruitment r WHERE CONCAT(r.title, ' ', r.address, ' ', r.salary) LIKE %?1%")
    Iterable<Recruitment> search2(String key);

    Iterable<Recruitment> findAllByOrderByDateBeginDesc();

    @Query(value = "select * from recruitment order by id desc ", nativeQuery = true)
    Iterable<Recruitment> sortNew();

    @Query(value = "select * from recruitment order by id asc  ", nativeQuery = true)
    Iterable<Recruitment> sortOdd();

    @Query(value = "select * from recruitment where status_recruitment_id = 2 or  status_recruitment_id = 3 ", nativeQuery = true)
    Page<Recruitment> findAllPaging(Pageable pageable);

    @Query(value = "select * from recruitment where profile_enterprise_id=:id order by id desc ", nativeQuery = true)
    Iterable<Recruitment> findRecruitmentByProfileEnterprise(@Param("id") Long id);

    @Modifying
    @Query(value = "select * from recruitment where address like %:q%", nativeQuery = true)
    Iterable<Recruitment> findRecruitmentByAddress(@Param("q") String q);

    @Query(value = "select * from recruitment where status_recruitment_id like 3 order by date_end desc  ", nativeQuery = true)
    Iterable<Recruitment> findRecruitmentByStatusRecruitmentId();

    @Query(value = "select *\n" +
            "from (select *\n" +
            "      from (select *\n" +
            "            from (select *\n" +
            "                  from (select *\n" +
            "                        from (select *\n" +
            "                              from (select *\n" +
            "                                    from recruitment\n" +
            "                                    where status_recruitment_id = 2 or status_recruitment_id = 3)\n" +
            "                                       as theoTrangThai\n" +
            "                              where address like %:address%)\n" +
            "                                 as theoTP\n" +
            "                        where title like %:title%)\n" +
            "                           as theoNghanh\n" +
            "                  where experience like %:experience%)\n" +
            "                     as theoKinhNghiem\n" +
            "            where salary between :min and :max) as theoluong) as theo_Recruitment\n" +
            "         join enterprise_table on theo_Recruitment.profile_enterprise_id = enterprise_table.id\n" +
            "where name_company like %:name%", nativeQuery = true)
    Iterable<Recruitment> findRecruitment(@Param("address") String address,
                                          @Param("title") String title,
                                          @Param("experience") String experience,
                                          @Param("min") Long min,
                                          @Param("max") Long max,
                                          @Param("name") String name
    );

}