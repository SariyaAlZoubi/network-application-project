package com.networkapplication.dtos;

import com.networkapplication.models.User;
import com.networkapplication.services.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements MainDTO {
    private Long user_id;
    private String user_name;
    private List<String> user_groups = new ArrayList<>();
    private List<String> groups = new ArrayList<>();
    private List<String> files = new ArrayList<>();
    private List<String> my_files = new ArrayList<>();

    private Utils.role role;

    public UserDTO(User user) {
        user_id = user.getId();
        user_name = user.getUsername();
        role=user.getRole();
        if (user.getUserGroups() != null)
            for (int i = 0; i < user.getUserGroups().size(); i++) {
                user_groups.add(user.getUserGroups().get(i).getGroupName());
            }
        if (user.getGroups() != null)
            for (int i = 0; i < user.getGroups().size(); i++) {
                groups.add(user.getGroups().get(i).getGroupName());
            }
        if (user.getFiles() != null)
            for (int i = 0; i < user.getFiles().size(); i++) {
                Long group_id=user.getFiles().get(i).getGroupFiles().getId();
                files.add(user.getFiles().get(i).getFileName().substring(0,user.getFiles().get(i).getFileName().length()-group_id.toString().length()));
            }
        if (user.getMyFiles() != null)
            for (int i = 0; i < user.getMyFiles().size(); i++) {
                Long group_id=user.getMyFiles().get(i).getGroupFiles().getId();
                my_files.add(user.getMyFiles().get(i).getFileName().substring(0,user.getMyFiles().get(i).getFileName().length()-group_id.toString().length()));
            }
    }
}
