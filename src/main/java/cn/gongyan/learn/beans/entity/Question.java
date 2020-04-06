/***********************************************************
 * @Description : 问题表实体
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@DynamicUpdate
public class Question {

    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    Integer questionId;
    String  questionTypeName;
    String questionDescription;
    String questionContent;
    String questionAnswers;
    String questionSelections;
    String questionOptionSplit;
}
