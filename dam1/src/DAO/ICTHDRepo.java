package DAO;

import entity.chitiethoadon;


import java.util.List;

public interface ICTHDRepo {
// tạo cho t 1 vài cái hàm chức năng CRUD ở đây
    // crud cho đứa nào chưa biết thì nó là thêm sửa xoá
    // interface chỉ có head code và k có body code
    public boolean add(chitiethoadon op);
    public boolean update(chitiethoadon op);
    public boolean delete(chitiethoadon op);
    public List<chitiethoadon> getAll( chitiethoadon op);
    public List<chitiethoadon> selectBySQL(String sql, Object... args);
    public List<chitiethoadon> findByID(int id );
    public List<chitiethoadon> findByName(String name );

}

