package lk.ijse.eca.memberservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "members")
public class Member {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime joinedAt;
    
    // Cloud storage image URL
    private String imageUrl;
}
