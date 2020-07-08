
public class MyQueue<E> implements Queue<E> {

	private Node<E> first = null;
	private Node<E> last = null;
	private int size = 0;

	@Override
	public void enqueue(E item) {
		// TODO Auto-generated method stub
		Node<E> temp = new Node(item, null);
		if(size==0){
			first = last = temp;
		 } else{
			last.setNext(temp);
			last = temp;
		}
		size++;
	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		E temp = first.getItem();

		if(size==1){
			first.setItem(null);
		}else {
			first = first.getNext();
		}
		size--;
		return temp;

	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		E temp = last.getItem();
		Node<E> i = first;
		if(size==1){
			last.setItem(null);
		}else {
			while (i.getNext() != last) {
				i = i.getNext();
			}
			i.setNext(null);
			last = i;
		}
		size--;
		return temp;
	}

	@Override
	public void clear() {
		first.setItem(null);
		first.setNext(null);
		last = first;
		size = 0;
	}

	@Override
	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public String toString() {
		Node<E> cursor = first;
		StringBuffer sb = new StringBuffer("(");
		while (cursor != null) {
			sb.append(cursor.getItem());
			if (cursor != last) {
				sb.append(' ');
			}
			cursor = cursor.getNext();
		}
		sb.append(")");
		return sb.toString();
	}

}
