package novel.learn.myNovel.manager.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import novel.learn.myNovel.core.constant.DatabaseConsts;
import novel.learn.myNovel.dao.entity.UserInfo;
import novel.learn.myNovel.dao.mapper.UserInfoMapper;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 用户模块的dao管理类
 * */
@Component
@RequiredArgsConstructor
public class UserDaoManager {
    private final UserInfoMapper userInfoMapper;

    /**
     * 根据用户ID集合批量查询用户信息列表
     *
     * @param userIds 需要查询的用户ID集合
     * @return 满足条件的用户信息列表
     */
    public List<UserInfo> listUsers(List<Long> userIds) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(DatabaseConsts.CommonColumnEnum.ID.getName(), userIds);
        return userInfoMapper.selectList(queryWrapper);
    }
}
