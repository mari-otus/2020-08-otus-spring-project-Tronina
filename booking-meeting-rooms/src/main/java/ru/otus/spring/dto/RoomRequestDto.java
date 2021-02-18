package ru.otus.spring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Запрос на создание переговорной комнаты.
 *
 * @author MTronina
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Room.Request", description = "Запрос на создание переговорной комнаты")
public class RoomRequestDto {

    @ApiModelProperty(value = "Идентификатор переговорной комнаты")
    private Long id;

    @NotBlank
    @ApiModelProperty(value = "Название переговорной комнаты", required = true)
    private String roomName;

    @NotNull
    @ApiModelProperty(value = "Вместимость комнаты (кол-во человек)", required = true)
    private Integer capacity;

    @ApiModelProperty(value = "Возможность проводить видеоконференции")
    private boolean hasConditioner;

    @ApiModelProperty(value = "Наличие кондиционера")
    private boolean hasVideoconference;

}
