import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { Translate, translate } from 'react-jhipster';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    data-cy="entity"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <MenuItem icon="asterisk" to="/category">
      <Translate contentKey="global.menu.entities.category" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/sub-category">
      <Translate contentKey="global.menu.entities.subCategory" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/action">
      <Translate contentKey="global.menu.entities.action" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/book-mark-action">
      <Translate contentKey="global.menu.entities.bookMarkAction" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/practice">
      <Translate contentKey="global.menu.entities.practice" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/practice-session">
      <Translate contentKey="global.menu.entities.practiceSession" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
