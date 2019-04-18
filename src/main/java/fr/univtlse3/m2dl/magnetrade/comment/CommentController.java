package fr.univtlse3.m2dl.magnetrade.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping({"/create", "/edit"})
    public Comment createComment(@RequestBody Comment comment) {
        return this.commentService.createOrUpdateComment(comment);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable Long id) {
        this.commentService.deleteComment(id);
    }

}
