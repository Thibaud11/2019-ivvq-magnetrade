package fr.univtlse3.m2dl.magnetrade.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping({"/create", "/edit"})
    public Comment createOrUpdateComment(@RequestBody Comment comment) {
        return this.commentService.createOrUpdateComment(comment);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable Long id) {
        this.commentService.deleteComment(id);
    }

    @GetMapping("/{id}")
    public Comment findById(@PathVariable Long id) {
        return this.commentService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public List<Comment> findAll() {
        return this.commentService.findAll();
    }

}
