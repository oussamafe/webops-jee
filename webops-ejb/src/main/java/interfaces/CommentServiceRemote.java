package interfaces;

import javax.ejb.Remote;

import entities.Comment;
import entities.Post;

@Remote
public interface CommentServiceRemote {

	public void addComment(Comment comment);

	public void deleteComment(int id);

	public Comment findComment(int id);

	public void updateComment(Comment comment);
	
	

}
