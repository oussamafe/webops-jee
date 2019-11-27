package interfaces;

import javax.ejb.Remote;

import entities.Post;

@Remote
public interface PostServiceRemote {
	
	public void addPost(Post post);
	public void deletePost(int id_post);
	public Post findPost(int id_post);
	public void updatePost(Post post);

}
