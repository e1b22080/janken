package oit.is.z2636.kaizi.janken.model;

import java.util.ArrayList;

//import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT * from users")
  ArrayList<User> selectAllUser();

  @Select("SELECT name from users where id = #{Id}")
  String selectById(int Id);

  @Select("SELECT id from users where name = #{Name}")
  int selectByName(String Name);

}
