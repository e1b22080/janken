package oit.is.z2636.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchMapper {

  @Select("SELECT * from MATCHES")
  ArrayList<Match> selectAllResult();

  @Insert("INSERT INTO MATCHES (user1, user2, user1Hand, user2Hand, isActive) VALUES (#{user1}, #{user2}, #{user1Hand}, #{user2Hand}, 'true')")
  void insertmatches(Match match);

  @Update("UPDATE MATCHES SET isActive = true WHERE ID = #{id}")
  void updateById(Match match);

}
