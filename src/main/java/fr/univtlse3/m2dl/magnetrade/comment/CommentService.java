package fr.univtlse3.m2dl.magnetrade.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * Method to create or update a comment.
     * @param comment the comment to create or update
     */
    public Comment createOrUpdateComment(Comment comment) {
        return this.commentRepository.save(comment);
    }

    /**
     * Method to delete a comment.
     * @param id id of the comment to delete
     */
    public void deleteComment(Long id) {
        this.commentRepository.deleteById(id);
    }

    /**
     * Method to find a comment.
     * @param id id fo the comment to find
     * @return the comment
     */
    public Optional<Comment> findById(Long id) {
        return this.commentRepository.findById(id);
    }

    /**
     * Method to all comments.
     * @return all comments
     */
    public List<Comment> findAll() {
        return this.commentRepository.findAll();
    }

}
