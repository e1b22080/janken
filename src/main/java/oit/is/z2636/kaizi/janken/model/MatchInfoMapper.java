package oit.is.z2636.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
// import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchInfoMapper {

  @Select("SELECT * FROM MatchInfo WHERE isActive = true")
  ArrayList<MatchInfo> selectActive();

  @Select("SELECT user1Hand FROM MATCHINFO WHERE isActive = true and user1 = #{user1} and user2 = #{user2}")
  String selectActiveMyId(int user1, int user2);

  @Insert("INSERT INTO MATCHINFO (user1, user2, user1Hand, isActive) VALUES (#{user1}, #{user2}, #{user1Hand}, 'true')")
  void insertmatchInfo(MatchInfo matchInfo);

  @Update("UPDATE MATCHINFO SET isActive = true WHERE ID = #{id}")
  void updateById(MatchInfo matchInfo);

}
