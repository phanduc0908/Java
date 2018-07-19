public class MyList
 {Node head,tail;
   MyList() {head=tail=null;}
   boolean isEmpty() {return(head==null);}
   void clear() {head=tail=null;}

   // (1) 
   void addLast(Person x)
     {Node q = new Node(x);
       if(isEmpty()) {head=tail=q;return;}
       tail.next = q;tail=q;
     }

    void visit(Node p) {if(p!=null) System.out.print(p.info);}
    void traverse()
     {Node p=head;
       while(p!=null)
         {visit(p);
           p=p.next;
         }
      System.out.println();
     }
   void addMany(String [] a, int [] b)
     {int n,i; n=a.length;
       for(i=0;i<n;i++) addLast(new Person(a[i],b[i]));
     }

   // (2)
   Node searchByName(String xName)
     {Node p=head;
       while(p!=null)
        {if(p.info.name.equals(xName)) return(p);
          p=p.next;
        }
       return(null);
     }

   Node searchByAge(int xAge)
     {Node p=head;
       while(p!=null)
        {if(p.info.age==xAge) return(p);
          p=p.next;
        }
       return(null);
     }

   // (3)
   void addFirst(Person x)
     {head = new Node(x,head);
       if(tail==null) tail=head;
     }

   // (4)
   void insertAfter(Node q, Person x)
     {if(q==null) return;
       Node r = q.next;
       Node u = new Node(x,r);
       q.next = u;
       if(tail==q) tail=u;
     }

   // (5)
   void insertBefore(Node q, Person x)
     {if(q==null) return;
      Node f,p; f=null; p=head;
      while(p!=null)
        {if(p==q) break;
          f=p; p=p.next;
        }
       if(p==null) return;
       if(f==null) // p is the head 
         {addFirst(x); return;
         }
       insertAfter(f,x);
     }

   // (6)
    void dele(Node q)
     {if(q==null) return;
       if(isEmpty()) return;
       Node f, p; f=null; p = head;
       while(p!=null)
         {if(p==q) break;
           f=p;
           p=p.next;
          }
       if(p==null) return;
       if(f==null)
         {head = head.next;
           return;
         }
       f.next = p.next;
       if(f.next==null) tail=f; 
     }

   // (7)
   void dele(String xName)
     {Node q = searchByName(xName);
       dele(q);
     }

   // (8)
   void dele(int xAge)
     {dele(searchByAge(xAge));
     }

   // (9)
   void deleAll(int xAge)
     {Node q=null;
       while(true)
        {q=searchByAge(xAge);
          if(q==null) break;
          dele(q);
        }
     }

   // (10)
   Node pos(int k)
     {int i=0;
       Node p = head;
       while(p!=null)
         {if(i==k) return(p);
           i++;
           p=p.next;
         }
      return(null);
     }

   // (11)
   void delePos(int k)
     {Node q = pos(k);
       dele(q);
     }

   // (12)
   void sortByName()
     {Node pi,pj; Person x;
       pi=head;
       while(pi!=null)
        {pj=pi.next;
          while(pj!=null)
            {if(pj.info.name.compareTo(pi.info.name)<0)
                {x=pi.info;pi.info=pj.info;pj.info=x;}
              pj=pj.next;
            }
          pi=pi.next;
        }
     }

   // (13)
   void sortByAge()
     {Node pi,pj; Person x;
       pi=head;
       while(pi!=null)
        {pj=pi.next;
          while(pj!=null)
            {if(pj.info.age<pi.info.age)
                {x=pi.info;pi.info=pj.info;pj.info=x;}
              pj=pj.next;
            }
          pi=pi.next;
        }
     }

   // (14)
   int size()
     {int i=0;
       Node p = head;
       while(p!=null)
         {i++;
           p=p.next;
         }
      return(i);
     }

   // (15)
   Person [] toArray()
     {int n=size();
      Person [] a = new Person[n];
      int i=0;
      Node p=head;
      while(p!=null)
        {a[i++] = new Person(p.info.name,p.info.age);
          p=p.next;
        }
      return(a);
     }

   // (16)
   void reverse()
     {MyList h = new MyList();
       Node p=head;
       while(p!=null)
        {h.addFirst(new Person(p.info.name,p.info.age));
          p=p.next;
        }
       clear();
       head = h.head;
       tail=h.tail;
     }

  // (17) 
  Node findMaxAge()
     {Node p, q; int t;
       p = head; 
       q = p;
       t = p.info.age;
       while(p!=null)
         {if(p.info.age>t) {t = p.info.age; q = p; }
           p = p.next;
         }
       return(q);
     }

  // (18) 
  Node findMinAge()
     {Node p, q; int t;
       p = head; 
       q = p;
       t = p.info.age;
       while(p!=null)
         {if(p.info.age<t) {t = p.info.age; q = p; }
           p = p.next;
         }
       return(q);
     }

  // (19) 
  void setData(Node p, Person x)
     {if(p==null) return;
       p.info = x;
     }

  // (20) 
  void sortByAge(int  k, int h)
     {if(k>h) return;
       Node u = pos(k);
       Node v = pos(h+1);
       Node pi,pj; Person x;
       pi=u;
       while(pi!=v)
        {pj=pi.next;
          while(pj!=v)
            {if(pj.info.age<pi.info.age)
                {x=pi.info;pi.info=pj.info;pj.info=x;}
              pj=pj.next;
            }
          pi=pi.next;
        }
     }
 
  void reverse(Person [] a, int k, int h) // reverse from k to h
     {int n = a.length;
      if(k>=h) return;
      if(k<0) k=0;
      if(h>n-1) h = n-1;
      int i,j; Person x;
      i=k;j=h;
      while(i<j)
       {x=a[i];a[i]=a[j];a[j]=x;
        i++;j--;
       }
     }
 
  // (21) 
  void reverse(int k, int h) // reverse from k to h 
     {int n,i; n = size();
      if(k>=h) return;
      if(k<0) k=0;
      if(h>n-1) h = n-1;
      Person [] a = toArray();
      reverse(a,k,h);
      clear();
      for(i=0;i<n;i++) addLast(a[i]);
     }
 }
