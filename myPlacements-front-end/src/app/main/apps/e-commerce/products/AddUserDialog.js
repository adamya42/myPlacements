import React, { useEffect, useCallback } from "react";
import {
  TextField,
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  Icon,
  IconButton,
  Typography,
  Toolbar,
  AppBar,
  Avatar,
} from "@material-ui/core";
import { useForm } from "@fuse/hooks";
import FuseUtils from "@fuse/FuseUtils";
import * as Actions from "../store/actions";
import { useDispatch, useSelector } from "react-redux";
import { showMessage } from "app/store/actions";

const defaultFormState = {
  email: "",

  roleId: "",
};

function AddUserDialog(props) {
  const dispatch = useDispatch();
  const addUserDialog = useSelector(
    ({ eCommerceApp }) => eCommerceApp.products.addUserDialog
  );

  const { form, handleChange, setForm } = useForm(defaultFormState);
  const initDialog = useCallback(() => {
    /**
     * Dialog type: 'edit'
     */
    // if (addUserDialog.type === "edit" && addUserDialog.data) {
    //   setForm({ ...addUserDialog.data });
    // }

    /**
     * Dialog type: 'new'
     */
    if (addUserDialog.type === "new") {
      setForm({
        ...defaultFormState,
        // ...addUserDialog.data,
        // id: FuseUtils.generateGUID(),
      });
    }
    //}, [addUserDialog.data, addUserDialog.type, setForm]);
  }, [addUserDialog.type, setForm]);

  useEffect(() => {
    /**
     * After Dialog Open
     */
    if (addUserDialog.props.open) {
      initDialog();
    }
  }, [addUserDialog.props.open, initDialog]);

  function closeComposeDialog() {
    // addUserDialog.type === "edit"
    //   ? dispatch(Actions.closeEditaddUserDialog())
    //   :
    dispatch(Actions.closeAddUserDialog());
  }

  function canBeSubmitted() {
    return form.email.length > 0;
  }

  function handleSubmit(event) {
    event.preventDefault();

    //console.log(form);

    if (addUserDialog.type === "new") {
      dispatch(Actions.addSingleUser(form));
    }

    closeComposeDialog();
  }

  // function handleSubmit() {
  //   dispatch(Actions.addSingleUser(form));
  // }

  function handleRemove() {
    // dispatch(Actions.removeContact(form.id));
    closeComposeDialog();
  }

  return (
    <Dialog
      classes={{
        paper: "m-24",
      }}
      {...addUserDialog.props}
      onClose={closeComposeDialog}
      fullWidth
      maxWidth="xs"
    >
      <AppBar position="static" elevation={1}>
        <Toolbar className="flex w-full">
          <Typography variant="subtitle1" color="inherit">
            {addUserDialog.type === "new" ? "New User" : "Edit Contact"}
          </Typography>
        </Toolbar>
        <div className="flex flex-col items-center justify-center pb-24">
          <Avatar
            className="w-96 h-96"
            alt="contact avatar"
            src={form.avatar}
          />
          {addUserDialog.type === "edit" && (
            <Typography variant="h6" color="inherit" className="pt-8">
              {form.name}
            </Typography>
          )}
        </div>
      </AppBar>
      <form
        noValidate
        onSubmit={handleSubmit}
        className="flex flex-col overflow-hidden"
      >
        <DialogContent classes={{ root: "p-24" }}>
          {/*
          <div className="flex">
            <div className="min-w-48 pt-20">
              <Icon color="action">person_add</Icon>
            </div>

            <TextField
              className="mb-24"
              label="Name"
              autoFocus
              id="name"
              name="name"
              value={form.name}
              onChange={handleChange}
              variant="outlined"
              fullWidth
            />
          </div>
        */}

          <div className="flex">
            <div className="min-w-48 pt-20">
              <Icon color="action">email</Icon>
            </div>
            <TextField
              className="mb-24"
              label="Email"
              id="email"
              name="email"
              value={form.email}
              onChange={handleChange}
              variant="outlined"
              fullWidth
              required
            />
          </div>
          <div className="flex">
            <div className="min-w-48 pt-20">
              <Icon color="action">work</Icon>
            </div>
            <TextField
              className="mb-24"
              label="Role"
              id="roleId"
              name="roleId"
              value={form.roleId}
              onChange={handleChange}
              variant="outlined"
              fullWidth
              required
            />
          </div>
        </DialogContent>
        {
          //addUserDialog.type === "new" ? (
          <DialogActions className="justify-between pl-16">
            <Button
              variant="contained"
              color="primary"
              onClick={handleSubmit}
              type="submit"
              disabled={!canBeSubmitted()}
            >
              Add
            </Button>
          </DialogActions>
          // ) : (
          //   <DialogActions className="justify-between pl-16">
          //     <Button
          //       variant="contained"
          //       color="primary"
          //       type="submit"
          //       onClick={handleSubmit}
          //       disabled={!canBeSubmitted()}
          //     >
          //       Save
          //     </Button>
          //     <IconButton onClick={handleRemove}>
          //       <Icon>delete</Icon>
          //     </IconButton>
          //   </DialogActions>
          // )
        }
      </form>
    </Dialog>
  );
}

export default AddUserDialog;
